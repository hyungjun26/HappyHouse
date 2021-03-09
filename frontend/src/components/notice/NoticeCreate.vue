<template>
  <div>
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">
        <div class="d-flex justify-content-between align-items-center">
          <h2>Happy House > 공지사항 > 글 작성</h2>
        </div>
      </div>
    </section>
    <div class="row">
      <div class="col-md-4"></div>
      <div class="col-md-4">
        <div class="form-group">
          <label for="noticeWriter">작성자</label>
          <input
            type="text"
            class="form-control"
            id="noticeWriter"
            ref="noticeWriter"
            value
            disabled
            placeholder="작성자를 입력하세요"
            v-model="noticeWriter"
          />
        </div>
        <div class="form-group">
          <label for="noticeTitle">제목</label>
          <input
            type="text"
            class="form-control"
            id="noticeTitle"
            ref="noticeTitle"
            placeholder="제목을 입력하세요"
            v-model="noticeTitle"
          />
        </div>
        <div class="form-group">
          <label for="noticeContent">내용</label>
          <textarea
            type="text"
            class="form-control"
            id="noticeContnet"
            ref="noticeContent"
            placeholder="내용을 입력하세요"
            v-model="noticeContent"
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
  name: "NoticeCreate",
  data: function() {
    return {
      noticeNo: "",
      noticeTitle: "",
      noticeContent: "",
      noticeWriter: ""
    };
  },
  methods: {
    checkHandler() {
      let err = true;
      let msg = "";
      !this.noticeWriter &&
        ((msg = "작성자를 입력해주세요"),
        (err = false),
        this.$refs.noticeWriter.focus());
      err &&
        !this.noticeTitle &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.noticeTitle.focus());
      err &&
        !this.noticeContent &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.noticeContent.focus());

      if (!err) alert(msg);
      else this.createHandler();
    },
    createHandler() {
      axios
        .post("http://localhost:8080/api/notice", {
          noticeWriter: this.noticeWriter,
          noticeTitle: this.noticeTitle,
          noticeContent: this.noticeContent
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
      this.$router.push("/NoticeList");
    },
    getFormatDate(regtime) {
      return moment(new Date(regtime)).format("YYYY.MM.DD");
    }
  },
  created() {
    let user = JSON.parse(sessionStorage.getItem("login_user"));
    this.noticeWriter = user.id;
  }
};
</script>

<style></style>
