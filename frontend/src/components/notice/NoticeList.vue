<template>
  <div>
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">
        <div class="d-flex justify-content-between align-items-center">
          <h2>Happy House > 공지사항</h2>
        </div>
      </div>
    </section>
    <div class="row">
      <div class="col-md-4"></div>
      <div class="col-md-4">
        <div v-if="items.length">
          <table class="table table-bordered table-condensed">
            <colgroup>
              <col :style="{ width: '5%' }" />
              <col :style="{ width: '50%' }" />
              <col :style="{ width: '10%' }" />
              <col :style="{ width: '25%' }" />
            </colgroup>
            <tr>
              <th>번호</th>
              <th>제목</th>
              <th>작성자</th>
              <th>날짜</th>
            </tr>
            <tr v-for="(item, index) in items" :key="index + '_items'">
              <td>{{ item.noticeNo }}</td>
              <td>
                <router-link :to="'NoticeDetail?noticeNo=' + item.noticeNo">
                  {{
                  item.noticeTitle
                  }}
                </router-link>
              </td>
              <td>{{ item.noticeWriter }}</td>
              <td>{{ getFormatDate(item.noticeDatetime) }}</td>
            </tr>
          </table>
        </div>
        <div v-else>공지사항 글이 없습니다.</div>
      </div>
    </div>
    <template v-if="grade==1">
      <div class="text-center">
        <button class="btn btn-primary" @click="movePage">등록</button>
      </div>
    </template>
  </div>
</template>

<script>
import axios from "axios";
import moment from "moment";
const storage = window.sessionStorage;
export default {
  name: "NoticeList",
  data() {
    return {
      items: [],
      grade: ""
    };
  },
  created() {
    let token = storage.getItem("jwt-auth-token");
    if (token == null || token == "") {
      alert("회원이 아니면 이용할 수 없습니다.");
      this.$router.push("/");
    } else {
      let user = JSON.parse(sessionStorage.getItem("login_user"));
      this.grade = user.grade;
      axios({
        method: "get",
        url: "http://localhost:8080/api/notice"
      }).then(({ data }) => {
        console.dir(data);
        this.items = data;
        console.log(this.items);
      });
    }
  },
  methods: {
    getFormatDate(regtime) {
      return moment(new Date(regtime)).format("YYYY.MM.DD");
    },
    movePage() {
      this.$router.push("/NoticeCreate");
    }
  }
};
</script>

<style></style>
