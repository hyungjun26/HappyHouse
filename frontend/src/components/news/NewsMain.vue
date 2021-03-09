<template>
  <div>
    <br />

    <div class="text-center">
      <section id="breadcrumbs" class="breadcrumbs">
        <div class="d-flex justify-content-between align-items-center">
          <h2>Happy House > 뉴스 > 상세보기</h2>
        </div>
      </section>
      <br />
      <br />
      <br />
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
        <button class="btn btn-primary" @click.prevent="search(s_dong)">검색</button>
      </div>
    </div>
    <div v-if="nlist.length">
      <table class="table table-bordered table-condensed">
        <colgroup>
          <col :style="{ width: '70%' }" />
          <col :style="{ width: '30%' }" />
        </colgroup>
        <tr>
          <th>뉴스 제목</th>
          <th>날짜</th>
        </tr>
        <tr v-for="(item, index) in nlist" :key="index + '_items'">
          <td>
            <router-link
              :to="{path:'NewsDetail',query:{title: removetag(item.title), originallink : item.originallink, description : item.description}}"
            >{{ removetag(item.title) }}</router-link>
          </td>
          <td>{{ getFormatDate(item.pubDate) }}</td>
        </tr>
      </table>
    </div>

    <div v-else class="text-center">지역 설정 후 검색해 주세요.</div>

    <br />
    <br />
    <br />
  </div>
</template>

<script>
import axios from "axios";
import moment from "moment";
const storage = window.sessionStorage;
export default {
  data() {
    return {
      nlist: [],
      sidos: {},
      guguns: {},
      dongs: {},
      s_sido: "",
      s_gugun: "",
      s_dong: ""
    };
  },
  methods: {
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
    search(val) {
      axios({
        method: "get",
        url: "http://localhost:8080/api/news/" + val
      }).then(({ data }) => {
        //console.dir(data);
        this.nlist = data;
        //console.log(this.items);
      });
    },
    getFormatDate(regtime) {
      return moment(new Date(regtime)).format("YYYY.MM.DD");
    },
    removetag(val) {
      let removetag = val.replace(/(<([^>]+)>)/gi, "");
      return removetag.replace(/&quot;/gi, " ");
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
    }
  }
};
</script>

<style>
</style>