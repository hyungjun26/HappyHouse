<template>
  <div>
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">
        <div class="d-flex justify-content-between align-items-center">
          <h2>Happy House > 공지사항 > 상세조회</h2>
        </div>
      </div>
    </section>
    <div class="row">
      <div class="col-md-4"></div>
      <div class="col-md-4">
        <table class="table table-bordered">
          <tr>
            <th>번호</th>
            <td>{{ item.noticeNo }}</td>
          </tr>
          <tr>
            <th>글쓴이</th>
            <td>{{ item.noticeWriter }}</td>
          </tr>
          <tr>
            <th>제목</th>
            <td>{{ item.noticeTitle }}</td>
          </tr>
          <tr>
            <th>공지사항 등록 날짜</th>
            <td>{{ getFormatDate(item.noticeDatetime) }}</td>
          </tr>
          <tr>
            <th>공지사항 내용</th>
            <td>{{ item.noticeContent }}</td>
          </tr>
        </table>
      </div>
      <div class="col-md-4"></div>
    </div>
    <br />
    <div class="text-center">
      <router-link :to="'/NoticeList'">
        <button class="btn btn-primary">목록</button>
      </router-link>
      <template v-if="id == writer">
        <router-link :to="'/NoticeUpdate?noticeNo=' + item.noticeNo">
          <button class="btn btn-primary">수정하기</button>
        </router-link>
      </template>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import moment from "moment";
export default {
  name: "NoticeDetail",
  data: function() {
    return {
      item: {},
      id: "",
      grade: "",
      writer: ""
    };
  },
  created() {
    const params = new URL(document.location).searchParams;
    let user = JSON.parse(sessionStorage.getItem("login_user"));
    this.id = user.id;
    this.grade = user.grade;
    axios
      .get(`http://localhost:8080/api/notice/${params.get("noticeNo")}`)
      .then(({ data }) => {
        console.dir(data);
        this.writer = data.noticeWriter;
        this.item = data;
      });
  },
  methods: {
    getFormatDate(regtime) {
      if (regtime != null) {
        return moment(new Date(regtime)).format("YYYY.MM.DD HH:mm:ss");
      } else return null;
    }
  }
};
</script>

<style></style>
