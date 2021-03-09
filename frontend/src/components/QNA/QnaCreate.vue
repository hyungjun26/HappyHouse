<template>
  <div>
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">
        <div class="d-flex justify-content-between align-items-center">
          <h2>Happy House > QnA > QnA 작성</h2>
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
            value
            disabled
            v-model="qnaUserid"
          />
        </div>
        <div class="form-group">
          <label for="qnaTitle">제목</label>
          <input
            type="text"
            class="form-control"
            id="qnaTitle"
            ref="qnaTitle"
            placeholder="제목을 입력하세요"
            v-model="qnaTitle"
          />
        </div>
        <div class="form-group">
          <label for="qnaContent">내용</label>
          <textarea
            type="text"
            class="form-control"
            id="qnaContnet"
            ref="qnaContent"
            placeholder="내용을 입력하세요"
            v-model="qnaContent"
          ></textarea>
        </div>
      </div>
      <div></div>
    </div>
    <div class="text-center">
      <button class="btn btn-primary" @click="checkHandler">등록</button>
      <button class="btn btn-primary" @click="moveList">목록</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import moment from "moment";
export default {
  name: "QnaCreate",
  data: function() {
    return {
      qnaNo: "",
      qnaTitle: "",
      qnaContent: "",
      qnaUserid: "",
      replyContent: "",
      replyUserid: ""
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

      if (!err) alert(msg);
      else this.createHandler();
    },
    createHandler() {
      axios
        .post("http://localhost:8080/api/qna", {
          qnaUserid: this.qnaUserid,
          qnaTitle: this.qnaTitle,
          qnaContent: this.qnaContent
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        });
    },
    moveList() {
      this.$router.push("/QnaList");
    },
    getFormatDate(regtime) {
      return moment(new Date(regtime)).format("YYYY.MM.DD");
    }
  },
  created() {
    let user = JSON.parse(sessionStorage.getItem("login_user"));
    this.qnaUserid = user.id;
  }
};
</script>

<style></style>
