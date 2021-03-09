<template>
  <div>
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">
        <div class="d-flex justify-content-between align-items-center">
          <h2>Happy House > 공지사항 > 수정</h2>
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
            ref="noticeTtitle"
            placeholder="제목을 입력하세요"
            v-model="noticeTitle"
          />
        </div>
        <div class="form-group">
          <label for="noticeContent">Qna내용</label>
          <textarea
            type="text"
            class="form-control"
            id="noticeContent"
            ref="noticeContent"
            placeholder="내용을 입력하세요"
            v-model="noticeContent"
          ></textarea>
        </div>
      </div>
      <div></div>
    </div>

    <div class="text-center">
      <button class="btn btn-primary" @click="checkHandler">수정</button>
      <router-link :to="'/NoticeDelete?noticeNo=' + noticeNo">
        <button class="btn btn-primary">삭제</button>
      </router-link>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import moment from "moment";
export default {
  name: "NoticeUpdate",
  data: function() {
    return {
      noticeNo: "",
      noticeTitle: "",
      noticeContent: "",
      noticeWriter: "",
      noticeDatetime: ""
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
      else this.updateHandler();
    },
    updateHandler() {
      axios
        .put(`http://localhost:8080/api/notice/${this.noticeNo}`, {
          noticeNo: this.noticeNo,
          noticeDatetime: this.noticeDatetime,
          noticeWriter: this.noticeWriter,
          noticeTitle: this.noticeTitle,
          noticeContent: this.noticeContent
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.$router.push("/NoticeList");
        });
    }
  },
  created() {
    axios
      .get(`http://localhost:8080/api/notice/${this.$route.query.noticeNo}`)
      .then(({ data }) => {
        this.noticeNo = data.noticeNo;
        this.noticeDatetime = data.noticeDatetime;
        this.noticeWriter = data.noticeWriter;
        this.noticeTitle = data.noticeTitle;
        this.noticeContent = data.noticeContent;
      });
  },
  getFormatDate(regtime) {
    return moment(new Date(regtime)).format("YYYY.MM.DD");
  }
};
</script>

<style></style>
