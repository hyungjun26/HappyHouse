<template>
  <div>
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">
        <div class="d-flex justify-content-between align-items-center">
          <h2>Happy House > QnA > 수정</h2>
        </div>
      </div>
    </section>
    <div class="row">
      <div class="col-md-4"></div>
      <div class="col-md-4">
        <div class="form-group">
          <label for="qnaUserid">작성자</label>
          <input
            type="text"
            class="form-control"
            id="qnaUserid"
            ref="qnaUserid"
            placeholder="작성자를 입력하세요"
            v-model="qnaUserid"
          />
        </div>
        <div class="form-group">
          <label for="qnaTitle">제목</label>
          <input
            type="text"
            class="form-control"
            id="qnaTitle"
            ref="qnaTtitle"
            placeholder="제목을 입력하세요"
            v-model="qnaTitle"
          />
        </div>
        <div class="form-group">
          <label for="qnaContent">Qna내용</label>
          <textarea
            type="text"
            class="form-control"
            id="qnaContent"
            ref="qnaContent"
            placeholder="내용을 입력하세요"
            v-model="qnaContent"
          ></textarea>
        </div>
        <div class="form-group">
          <label for="reply">Qna답변자</label>
          <textarea
            type="text"
            class="form-control"
            id="replyUserid"
            ref="replyUserid"
            placeholder="답변자를 입력하세요"
            disabled
            v-model="replyUserid"
          ></textarea>
        </div>
        <div class="form-group">
          <label for="replyContent">Qna답변내용</label>
          <textarea
            type="text"
            class="form-control"
            id="replyContent"
            ref="replyContent"
            placeholder="답변 내용을 입력하세요"
            disabled
            v-model="replyContent"
          ></textarea>
        </div>
      </div>
      <div></div>
    </div>
    <div class="text-center">
      <button class="btn btn-primary" @click="checkHandler">수정</button>
      <router-link :to="'/QnaDelete?qnaNo=' + qnaNo">
        <button class="btn btn-primary">삭제</button>
      </router-link>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import moment from "moment";
export default {
  name: "QnaUpdate",
  data: function() {
    return {
      qnaNo: "",
      qnaTitle: "",
      qnaContent: "",
      qnaUserid: "",
      replyContent: "",
      replyUserid: "",
      qnaDatetime: "",
      replyDatetime: "",
      grade: ""
    };
  },
  methods: {
    checkHandler() {
      let err = true;
      let msg = "";
      !this.qnaUserid &&
        ((msg = "작성자를 입력해주세요"),
        (err = false),
        this.$refs.qnaUserid.focus());
      err &&
        !this.qnaTitle &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.qnaTitle.focus());
      err &&
        !this.qnaContent &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.qnaContent.focus());
      if (this.grade != "") {
        err &&
          !this.replyUserid &&
          ((msg = "답변 작성자를 입력해주세요"),
          (err = false),
          this.$refs.replyUserid.focus());
        err &&
          !this.replyContent &&
          ((msg = "답변 내용을 입력해주세요"),
          (err = false),
          this.$refs.replyContent.focus());
      }

      if (!err) alert(msg);
      else this.updateHandler();
    },
    updateHandler() {
      axios
        .put(`http://localhost:8080/api/qna/${this.qnaNo}`, {
          qnaNo: this.qnaNo,
          qnaDatetime: this.qnaDatetime,
          qnaUserid: this.qnaUserid,
          qnaTitle: this.qnaTitle,
          qnaContent: this.qnaContent,
          replyUserid: this.replyUserid,
          replyContent: this.replyContent,
          replyDatetime: this.replyDatetime
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.$router.push("/QnaList");
        });
    }
  },
  created() {
    axios
      .get(`http://localhost:8080/api/qna/${this.$route.query.qnaNo}`)
      .then(({ data }) => {
        this.qnaNo = data.qnaNo;
        this.qnaDatetime = data.qnaDatetime;
        this.qnaUserid = data.qnaUserid;
        this.qnaTitle = data.qnaTitle;
        this.qnaContent = data.qnaContent;
        this.replyUserid = data.replyUserid;
        this.replyContent = data.replyContent;
        this.replyDatetime = data.replyDatetime;
      });
    let user = JSON.parse(sessionStorage.getItem("login_user"));
    this.grade = user.grade;
  },
  getFormatDate(regtime) {
    return moment(new Date(regtime)).format("YYYY.MM.DD");
  }
};
</script>

<style></style>
