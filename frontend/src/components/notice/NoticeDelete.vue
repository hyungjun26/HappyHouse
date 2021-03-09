<template>
  <section id="breadcrumbs" class="breadcrumbs">
    <div class="container">
      <div class="d-flex justify-content-between align-items-center">
        <h2>Happy House > 공지사항 > 글 삭제</h2>
      </div>
    </div>
  </section>
</template>

<script>
import axios from "axios";
export default {
  name: "NoticeDelete",
  created() {
    const params = new URL(document.location).searchParams;
    axios
      .delete(`http://localhost:8080/api/notice/${params.get("noticeNo")}`)
      .then(({ data }) => {
        let msg = "삭제 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "공지사항 삭제가 완료되었습니다.";
        }
        alert(msg);
        this.$router.push("/NoticeList");
      });
  }
};
</script>

<style></style>
