<template>
  <div>
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">
        <div class="d-flex justify-content-between align-items-center">
          <h2>Happy House > 선호 지역</h2>
        </div>
      </div>
    </section>
    <div id="data_table" style="width:70%; margin: auto">
      <table class="table mt-2">
        <thead>
          <tr>
            <th>번호</th>
            <th>시도</th>
            <th>구군</th>
            <th>읍면동</th>
            <th>지도</th>
            <th>삭제</th>
          </tr>
        </thead>
        <tbody id="searchResult" v-for="(item, index) in plists" :key="index">
          <tr class="table-white">
            <td>{{item.preferNo}}</td>
            <td>{{item.sidoName}}</td>
            <td>{{item.gugunName}}</td>
            <td>{{item.dongName}}</td>
            <td>
              <button class="btn btn-primary" @click.prevent="googleMap(item.dongName)">지도</button>
            </td>
            <td>
              <button class="btn btn-primary" @click.prevent="deletePrefer(item.preferNo)">삭제</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <br />
    <template v-if="!listhidden">
      <div class="text-center">
        <button class="btn btn-primary" @click.prevent="addButtonActivate">지역추가</button>
      </div>
    </template>
    <br />
    <template v-if="!addhidden">
      <div class="text-center">
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
          <select id="dong" v-model="s_dong">
            <option value="0">선택</option>
            <option v-for="(item, index) in dongs" :key="index">{{ item.dong }}</option>
          </select>
        </div>
        <div class="text-center">
          <button class="btn btn-primary" @click.prevent="addList">선호지역추가</button>
        </div>
      </div>
    </template>
    <br />
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
  </div>
</template>

<script>
import axios from "axios";
const storage = window.sessionStorage;
export default {
  data() {
    return {
      addhidden: true,
      listhidden: false,
      plists: {},
      sidos: {},
      guguns: {},
      dongs: {},
      s_sido: "",
      s_gugun: "",
      s_dong: "",
      apts: {},
      puserId: "",
      m_center: { lat: 37.5665734, lng: 126.978179 },
      markers: [
        {
          position: {
            lat: 37.5665734,
            lng: 126.978179
          }
        }
      ]
    };
  },
  methods: {
    addButtonActivate() {
      this.addhidden = false;
      this.listhidden = true;
    },
    clickMarker() {
      this.$dialog.confirm({
        text: "How about GoogleMap?"
      });
    },
    changeSido() {
      axios({
        method: "get",
        url: "http://localhost:8080/api/select/gugun/" + this.s_sido
      })
        .then(res => {
          //console.log("gugun");
          //console.dir(res);
          this.guguns = res.data.data;
        })
        .catch(e => {
          console.dir(e);
        });
    },
    changeGugun() {
      axios({
        method: "get",
        url:
          "http://localhost:8080/api/select/dong?sido=" +
          this.s_sido +
          "&" +
          "gugun=" +
          this.s_gugun
      })
        .then(res => {
          this.dongs = res.data.data;
        })
        .catch(e => {
          console.dir(e);
        });
    },
    getSido() {
      axios({
        method: "get",
        url: "http://localhost:8080/api/select/sido"
      })
        .then(res => {
          // console.dir(res);
          this.sidos = res.data.data;
          // console.dir(this.sidos);
        })
        .catch(e => {
          console.dir(e);
        });
    },
    getPreferList() {
      axios({
        method: "get",
        url: "http://localhost:8080/api/prefer/" + this.puserId
      }).then(res => {
        console.dir(res);
        this.plists = res.data;
      });
    },
    addList() {
      axios({
        method: "post",
        url: "http://localhost:8080/api/prefer",
        data: {
          puserId: this.puserId,
          sidoName: this.s_sido,
          gugunName: this.s_gugun,
          dongName: this.s_dong
        }
      }).then(({ data }) => {
        let msg = "선호지역 등록 처리시 문제가 발생했습니다.";
        if (data == "success") {
          msg = "등록이 완료되었습니다.";
        }
        alert(msg);
        window.location.reload();
      });
    },
    deletePrefer(preferNo) {
      axios({
        method: "delete",
        url: "http://localhost:8080/api/prefer/" + preferNo
      }).then(({ data }) => {
        let msg = "삭제 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "선호지역 삭제가 완료되었습니다.";
        }
        alert(msg);
        window.location.reload();
      });
    },
    googleMap(dong) {
      this.apts = {};
      console.dir(this.markers);
      this.markers = [];

      axios({
        method: "get",
        url: "http://localhost:8080/api/select/apt/" + dong
      })
        .then(res => {
          console.log("apt");
          // console.dir(res);
          this.apts = res.data.data;
          let count = res.data.data.length;
          let sum_lat = 0;
          let sum_lng = 0;
          for (let i = 0; i < count; i++) {
            let clat = parseFloat(res.data.data[i].lat);
            let clng = parseFloat(res.data.data[i].lng);
            //console.log(clat);
            //console.log(clng);
            sum_lat += clat;
            sum_lng += clng;
            this.markers.push({
              position: {
                lat: clat,
                lng: clng
              }
            });
            //console.dir(this.markers);
          }
          this.m_center = {
            lat: parseFloat(sum_lat / count),
            lng: parseFloat(sum_lng / count)
          };
        })
        .catch(e => {
          console.dir(e);
        });
    }
  },
  created() {
    let token = storage.getItem("jwt-auth-token");
    if (token == null || token == "") {
      alert("회원이 아니면 이용할 수 없습니다.");
      this.$router.push("/");
    } else {
      this.getSido();
      let user = JSON.parse(sessionStorage.getItem("login_user"));
      this.puserId = user.id;
      this.getPreferList();
    }
  }
};
</script>

<style>
</style>