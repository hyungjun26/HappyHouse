<template>
  <div>
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">
        <div class="d-flex justify-content-between align-items-center">
          <h2>Happy House > 주택 거래정보 > 상세조회</h2>
        </div>
      </div>
    </section>
    <div class="row">
      <div class="col-md-4"></div>
      <div class="text-center col-md-4">
        <table class="table table-bordered">
          <thead>
            <th colspan="2">거래번호:{{ deal.no }}</th>
          </thead>
          <tbody>
            <tr>
              <td rowspan="4" align="center">
                <!-- src="https://raemian.co.kr/upload/estate/estate_74251745.jpg" -->
                <img :src="getUrl" alt width="250" height="200" />
              </td>
              <td>주택명:{{ deal.aptName }}</td>
            </tr>
            <tr>
              <td>지역(동):{{ deal.dong }}</td>
            </tr>
            <tr>
              <td v-if="deal.type == 1">거래유형:아파트 매매</td>
              <td v-if="deal.type == 2">거래유형:주택 매매</td>
              <td v-if="deal.type == 3">거래유형:아파트 전월세</td>
              <td v-if="deal.type == 4">거래유형:주택 전월세</td>
            </tr>
            <tr>
              <td>거래가격:{{ deal.dealAmount }}</td>
            </tr>
            <tr>
              <td rowspan="4" align="center">
                <GmapMap
                  :center="m_center"
                  :zoom="15"
                  map-type-id="roadmap"
                  style="width:350px; height:200px; margin:auto"
                >
                  <GmapMarker
                    :key="index"
                    v-for="(m, index) in markers"
                    :position="m.position"
                    :clickable="true"
                    :draggable="true"
                    :v-model="markers"
                    @click="clickMarker"
                  />
                </GmapMap>
              </td>
              <td>거래일시:{{ deal.dealYear }}.{{ deal.dealMonth }}.{{ deal.dealDay }}</td>
            </tr>
            <tr>
              <td>건축연도:{{ deal.buildYear }}</td>
            </tr>

            <tr>
              <td>면적:{{ deal.area }}m2</td>
            </tr>
            <tr>
              <td>거래층:{{ deal.floor }}층</td>
            </tr>
          </tbody>
          <tfoot>
            <tr>
              <td colspan="2">
                <line-chart :chart-data="datacollection" ref="LineChart"></line-chart>
              </td>
            </tr>
          </tfoot>
        </table>
      </div>
      <div class="col-sm-3"></div>
    </div>
    <div class="text-center">
      <router-link :to="'/DealList'">
        <button class="btn btn-success">목록으로</button>
      </router-link>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import LineChart from "@/components/LineChart";
export default {
  components: {
    LineChart
  },
  data() {
    return {
      deal: {},
      getUrl: "",
      m_center: { lat: 0, lng: 0 },
      markers: [],
      recentDeals: [],
      datacollection: null
    };
  },
  mounted() {
    this.fillData();
  },
  created() {
    const param = new URL(document.location).searchParams;
    const no = param.get("no");
    axios({
      url: "http://localhost:8080/api/deal/" + no,
      method: "get"
    }).then(res => {
      //console.log(res.data);
      this.deal = res.data;
      if (res.data.img == null) {
        this.getUrl = require("../../assets/img/noimage.jpg");
      } else {
        this.getUrl = require("../../assets/img/" + res.data.img);
      }

      this.m_center.lat = parseFloat(res.data.lat);
      this.m_center.lng = parseFloat(res.data.lng);
      this.markers.push({
        position: {
          lat: parseFloat(res.data.lat),
          lng: parseFloat(res.data.lng)
        }
      });
      //console.log(this.m_center.lat + ", " + this.m_center.lng);
      console.log(res.data);

      console.log(this.deal.aptNo);
      console.log(this.deal.type);
      axios({
        url: "http://localhost:8080/api/deal/recent",
        method: "post",
        data: {
          no: this.deal.no,
          aptNo: this.deal.aptNo,
          type: this.deal.type,
          area: this.deal.area
        }
      }).then(res => {
        console.log(res.data);
        this.fillData(res.data);
        //this.$refs.LineChart.addData();
      });
    });
  },
  methods: {
    clickMarker() {
      this.$dialog.confirm({
        text: "How about GoogleMap?"
      });
    },
    fillData(deals) {
      let label = [];
      let deal = [];
      for (let idx = deals.length - 1; idx >= 0; idx--) {
        const temp = deals[idx];
        label.push(temp.dealYear + "." + temp.dealMonth + "." + temp.dealDay);
        let amount = parseInt(temp.dealAmount.replace(",", "").trim());
        deal.push(amount);
      }
      this.datacollection = {
        labels: label,
        datasets: [
          {
            label: "최근 거래",
            //backgroundColor: "#f87979",
            data: deal
          }
        ]
      };
    }
  }
};
</script>

<style scoped></style>
