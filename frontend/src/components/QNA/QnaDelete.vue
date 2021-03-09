<template>
  <div>삭제중...</div>
</template>

<script>
import axios from "axios";
export default {
  name: "QnaDelete",
  created() {
    const params = new URL(document.location).searchParams;
    axios
      .delete(`http://localhost:8080/api/qna/${params.get("qnaNo")}`)
      .then(({ data }) => {
        let msg = "삭제 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "Qna 글 삭제가 완료되었습니다.";
        }
        alert(msg);
        this.$router.push("/QnaList");
      });
  }
};
</script>

<style></style>
