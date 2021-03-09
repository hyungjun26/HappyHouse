<template>
  <div>
    <!-- ======= Section1 ======= -->
    <section id="hero" class="d-flex align-items-center justify-content-center">
      <div class="container" data-aos="fade-up">
        <div class="row justify-content-center" data-aos="fade-up" data-aos-delay="150">
          <div class="col-xl-6 col-lg-8">
            <h1>
              HappyHouse
              <span>.</span>
            </h1>
            <h2>행복한 우리집</h2>
          </div>
        </div>

        <div class="row mt-5 justify-content-center" data-aos="zoom-in" data-aos-delay="250">
          <div class="col-xl-2 col-md-4 col-6">
            <div class="icon-box">
              <i clas="bx bxl-twitter"></i>
              <h3>
                <a href="#" @click.prevent="moveNotice">공지사항</a>
              </h3>
            </div>
          </div>

          <div class="col-xl-2 col-md-4 col-6">
            <div class="icon-box">
              <h3>
                <a href="#" @click.prevent="moveDeal">주택거래정보</a>
              </h3>
            </div>
          </div>
          <div class="col-xl-2 col-md-4 col-6 mt-4 mt-md-0">
            <div class="icon-box">
              <h3>
                <a href="#" @click.prevent="movePrefer">관심지역설정</a>
              </h3>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- <div>
      <h1 class="text-center">메인 페이지</h1>
    </div>

    <div id="local_selector" style="width:70%; height: 50px; margin:auto">
      시도 :
      <select id="sido" @change="changeSido" v-model="s_sido">
        <option value="0">선택</option>
        <option v-for="(item, index) in sidos" :key="index">{{ item.sido_name }}</option>
      </select>
      구군 :
      <select id="gugun" @change="changeGugun" v-model="s_gugun">
        <option value="0">선택</option>
        <option v-for="(item, index) in guguns" :key="index">{{ item.gugun_name }}</option>
      </select>
      읍면동 :
      <select id="dong" @change="changeDong" v-model="s_dong">
        <option value="0">선택</option>
        <option v-for="(item, index) in dongs" :key="index">{{ item.dong }}</option>
      </select>
    </div>
    <div id="data_table" style="width:70%; margin: auto">
      <table class="table mt-2">
        <thead>
          <tr>
            <th>번호</th>
            <th>법정동</th>
            <th>아파트이름</th>
            <th>지번</th>
            <th>지역코드</th>
            <th>위도</th>
            <th>경도</th>
          </tr>
        </thead>
        <tbody id="searchResult" v-for="(item, index) in apts" :key="index">
          <tr class="table-white">
            <td>{{item.no}}</td>
            <td>{{item.dong}}</td>
            <td>{{item.aptName}}</td>
            <td>{{item.jibun}}</td>
            <td>{{item.code}}</td>
            <td>{{item.lat}}</td>
            <td>{{item.lng}}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div>
      <GmapMap
        :center="m_center"
        :zoom="15"
        map-type-id="roadmap"
        style="width:500px; height:300px; margin:auto"
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
    </div>
    <div></div>-->
  </div>
</template>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<script>
import axios from "axios";
export default {
  name: "Index",
  methods: {
    moveDeal() {
      this.$router.push("/deallist");
    },
    movePrefer() {
      this.$router.push("/PreferArea");
    },
    moveNotice() {
      this.$router.push("/NoticeList");
    }
  }
  // data() {
  //   return {
  //     sidos: {},
  //     guguns: {},
  //     dongs: {},
  //     apts: {},
  //     s_sido: "",
  //     s_gugun: "",
  //     s_dong: "",
  //     m_center: { lat: 37.5665734, lng: 126.978179 },
  //     markers: [
  //       {
  //         position: {
  //           lat: 37.5665734,
  //           lng: 126.978179
  //         }
  //       }
  //     ]
  //   };
  // },
  // methods: {
  //   clickMarker() {
  //     this.$dialog.confirm({
  //       text: "How about GoogleMap?"
  //     });
  //   },
  //   changeSido() {
  //     axios({
  //       method: "get",
  //       url: "http://localhost:8080/api/select/gugun/" + this.s_sido
  //     })
  //       .then(res => {
  //         //console.log("gugun");
  //         //console.dir(res);
  //         this.guguns = res.data.data;
  //       })
  //       .catch(e => {
  //         console.dir(e);
  //       });
  //   },
  //   changeGugun() {
  //     axios({
  //       method: "get",
  //       url:
  //         "http://localhost:8080/api/select/dong?sido=" +
  //         this.s_sido +
  //         "&" +
  //         "gugun=" +
  //         this.s_gugun
  //     })
  //       .then(res => {
  //         //console.log("dong");
  //         //console.dir(res);
  //         this.dongs = res.data.data;
  //       })
  //       .catch(e => {
  //         console.dir(e);
  //       });
  //   },
  //   changeDong() {
  //     this.apts = {};
  //     console.dir(this.markers);
  //     this.markers = [];

  //     axios({
  //       method: "get",
  //       url: "http://localhost:8080/api/select/apt/" + this.s_dong
  //     })
  //       .then(res => {
  //         console.log("apt");
  //         // console.dir(res);
  //         this.apts = res.data.data;
  //         let count = res.data.data.length;
  //         let sum_lat = 0;
  //         let sum_lng = 0;
  //         for (let i = 0; i < count; i++) {
  //           let clat = parseFloat(res.data.data[i].lat);
  //           let clng = parseFloat(res.data.data[i].lng);
  //           //console.log(clat);
  //           //console.log(clng);
  //           sum_lat += clat;
  //           sum_lng += clng;
  //           this.markers.push({
  //             position: {
  //               lat: clat,
  //               lng: clng
  //             }
  //           });
  //           //console.dir(this.markers);
  //         }
  //         this.m_center = {
  //           lat: parseFloat(sum_lat / count),
  //           lng: parseFloat(sum_lng / count)
  //         };
  //       })
  //       .catch(e => {
  //         console.dir(e);
  //       });
  //   },
  //   getSido() {
  //     axios({
  //       method: "get",
  //       url: "http://localhost:8080/api/select/sido"
  //     })
  //       .then(res => {
  //         // console.dir(res);
  //         this.sidos = res.data.data;
  //         // console.dir(this.sidos);
  //       })
  //       .catch(e => {
  //         console.dir(e);
  //       });
  //   }
  // },
  // created() {
  //   this.getSido();
  // }
};
</script>
<style></style>
