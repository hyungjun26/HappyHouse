<template>
  <div>
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">
        <div class="d-flex justify-content-between align-items-center">
          <h2>Happy House > QnA > 상세조회</h2>
        </div>
      </div>
    </section>
    <div class="row">
      <div class="col-md-4"></div>
      <div class="col-md-4">
        <table class="table table-bordered text-center">
          <tr>
            <th>번호</th>
            <td>{{ item.qnaNo }}</td>
          </tr>
          <tr>
            <th>글쓴이</th>
            <td>{{ item.qnaUserid }}</td>
          </tr>
          <tr>
            <th>제목</th>
            <td>{{ item.qnaTitle }}</td>
          </tr>
          <tr>
            <th>QNA등록 날짜</th>
            <td>{{ getFormatDate(item.qnaDatetime) }}</td>
          </tr>
          <tr>
            <th>QNA내용</th>
            <td>{{ item.qnaContent }}</td>
          </tr>
          <tr>
            <th>답변자</th>
            <td>{{ item.replyUserid }}</td>
          </tr>
          <tr>
            <th>답변날짜</th>
            <td>{{ getFormatDate(item.replyDatetime) }}</td>
          </tr>
          <tr>
            <th>답변내용</th>
            <td>{{ item.replyContent }}</td>
          </tr>
        </table>
      </div>
      <div class="col-md-4"></div>
    </div>
    <br />
    <div class="text-center">
      <router-link :to="'/QnaList'">
        <button class="btn btn-primary">목록</button>
      </router-link>
      <template v-if="grade == 1">
        <router-link :to="'/QnaReply?qnaNo=' + item.qnaNo">
          <button class="btn btn-primary">답변달기</button>
        </router-link>
      </template>
      <template v-if="id == writer">
        <router-link :to="'/QnaUpdate?qnaNo=' + item.qnaNo">
          <button class="btn btn-primary">QNA수정하기</button>
        </router-link>
      </template>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import moment from "moment";
export default {
  name: "QnaDetail",
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
      .get(`http://localhost:8080/api/qna/${params.get("qnaNo")}`)
      .then(({ data }) => {
        console.dir(data);
        this.writer = data.qnaUserid;
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
