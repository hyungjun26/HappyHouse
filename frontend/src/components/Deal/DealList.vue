<template>
  <div>
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">
        <div class="d-flex justify-content-between align-items-center">
          <h2>Happy House > 주택 거래정보</h2>
        </div>
      </div>
    </section>
    <div class="row">
      <div class="col-md-4"></div>
      <div v-if="deals.length" class="col-xs-4">
        <table class="table table-condensed">
          <thead>
            <tr>
              <td colspan="1"></td>
              <td colspan="3">
                <div class="form-check-inline">
                  <label class="form-check-label">
                    <input type="checkbox" class="form-check-input" v-model="aptTrade" value />
                    아파트 매매
                  </label>
                </div>
                <div class="form-check-inline">
                  <label class="form-check-label">
                    <input type="checkbox" class="form-check-input" v-model="houseTrade" value />
                    주택 매매
                  </label>
                </div>
                <div class="form-check-inline">
                  <label class="form-check-label">
                    <input type="checkbox" class="form-check-input" v-model="aptRent" value />
                    아파트 전월세
                  </label>
                </div>
                <div class="form-check-inline">
                  <label class="form-check-label">
                    <input type="checkbox" class="form-check-input" v-model="houseRent" value />
                    주택 전월세
                  </label>
                </div>
              </td>
              <td colspan="1"></td>
            </tr>
            <tr>
              <td colspan="1" align="right">
                <select class="form-control" v-model="key">
                  <option value>검색유형</option>
                  <option value="dong">지역(동)</option>
                  <option value="name">주택명</option>
                </select>
              </td>
              <td colspan="3">
                <input type="text" class="form-control" v-model="word" @change="searchHandler()" />
              </td>
              <td colspan="1">
                <button class="btn btn-info" v-on:click="searchHandler()">검색</button>
              </td>
            </tr>
            <tr>
              <th>거래번호</th>
              <th>지역(동)</th>
              <th>주택명</th>
              <th>거래금액</th>
              <th>거래종류</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(deal, index) in deals" :key="index" style="height:30px">
              <td>{{ deal.no }}</td>
              <td>{{ deal.dong }}</td>
              <td>
                <router-link style="color:blue" :to="'dealdetail?no=' + deal.no">{{ deal.aptName }}</router-link>
                <!-- <router-link :to="'dealdetail'">{{ deal.aptName }}</router-link> -->
              </td>
              <td>{{ deal.dealAmount }}</td>
              <td v-if="deal.type == 1">아파트 매매</td>
              <td v-if="deal.type == 2">주택 매매</td>
              <td v-if="deal.type == 3">아파트 전월세</td>
              <td v-if="deal.type == 4">주택 전월세</td>
            </tr>
          </tbody>
        </table>
        <div class="text-center">
          <sliding-pagination
            :current="currentPage"
            :total="totalPages"
            @page-change="pageChangeHandler"
          ></sliding-pagination>
        </div>
      </div>
      <div v-else>
        <table class="table table-condensed">
          <tr>
            <td colspan="1"></td>
            <td colspan="3">
              <div class="form-check-inline">
                <label class="form-check-label">
                  <input type="checkbox" class="form-check-input" v-model="aptTrade" value />
                  아파트 매매
                </label>
              </div>
              <div class="form-check-inline">
                <label class="form-check-label">
                  <input type="checkbox" class="form-check-input" v-model="houseTrade" value />
                  주택 매매
                </label>
              </div>
              <div class="form-check-inline">
                <label class="form-check-label">
                  <input type="checkbox" class="form-check-input" v-model="aptRent" value />
                  아파트 전월세
                </label>
              </div>
              <div class="form-check-inline">
                <label class="form-check-label">
                  <input type="checkbox" class="form-check-input" v-model="houseRent" value />
                  주택 전월세
                </label>
              </div>
            </td>
            <td colspan="1"></td>
          </tr>
          <tr>
            <td colspan="1" align="right">
              <select class="form-control" v-model="key">
                <option value>검색유형</option>
                <option value="dong">지역(동)</option>
                <option value="name">주택명</option>
              </select>
            </td>
            <td colspan="3">
              <input type="text" class="form-control" v-model="word" @change="searchHandler()" />
            </td>
            <td colspan="1">
              <button class="btn btn-info" v-on:click="searchHandler()">검색</button>
            </td>
          </tr>
        </table>정보없음
      </div>
    </div>
  </div>
</template>

<script>
import SlidingPagination from "vue-sliding-pagination";
import axios from "axios";
export default {
  components: {
    SlidingPagination
  },
  data: function() {
    return {
      deals: [],
      search: "",
      aptTrade: true,
      aptRent: true,
      houseTrade: true,
      houseRent: true,
      key: "",
      word: "",
      currentPage: 1,
      totalPages: 0
    };
  },
  created() {
    axios({
      url: "http://localhost:8080/api/deal/search",
      method: "post",
      data: {
        aptTrade: true,
        aptRent: true,
        houseTrade: true,
        houseRent: true,
        key: "",
        word: "",
        currentPage: 1
      }
    })
      .then(response => {
        console.log(response.data.length);
        this.deals = response.data;
      })
      .catch(error => {
        console.log(error);
      })
      .finally(() => {
        console.log("작업 종료");
      });
    axios({
      url: "http://localhost:8080/api/deal/count",
      method: "post",
      data: {
        aptTrade: this.aptTrade,
        aptRent: this.aptRent,
        houseTrade: this.houseTrade,
        houseRent: this.houseRent,
        key: this.key,
        word: this.word
      }
    }).then(response => {
      console.log(response.data);
      let len = response.data;
      if (len % 20 == 0) {
        this.totalPages = parseInt(len / 20);
      } else {
        this.totalPages = parseInt(len / 20) + 1;
      }
    });
  },
  methods: {
    pageChangeHandler(selectedPage) {
      this.currentPage = selectedPage;
      axios({
        url: "http://localhost:8080/api/deal/search",
        method: "post",
        data: {
          aptTrade: this.aptTrade,
          aptRent: this.aptRent,
          houseTrade: this.houseTrade,
          houseRent: this.houseRent,
          key: this.key,
          word: this.word,
          currentPage: this.currentPage
        }
      }).then(response => {
        console.log(response.data.length);
        this.deals = response.data;
      });
    },
    searchHandler() {
      axios({
        url: "http://localhost:8080/api/deal/search",
        method: "post",
        data: {
          aptTrade: this.aptTrade,
          aptRent: this.aptRent,
          houseTrade: this.houseTrade,
          houseRent: this.houseRent,
          key: this.key,
          word: this.word,
          currentPage: 1
        }
      })
        .then(response => {
          this.deals = response.data;
        })
        .catch(error => {
          console.log(error);
        })
        .finally(() => {
          console.log("작업 종료");
        });
      axios({
        url: "http://localhost:8080/api/deal/count",
        method: "post",
        data: {
          aptTrade: this.aptTrade,
          aptRent: this.aptRent,
          houseTrade: this.houseTrade,
          houseRent: this.houseRent,
          key: this.key,
          word: this.word
        }
      }).then(response => {
        console.log(response.data);
        let len = response.data;
        if (len % 20 == 0) {
          this.totalPages = parseInt(len / 20);
        } else {
          this.totalPages = parseInt(len / 20) + 1;
        }
      });
    }
  }
};
</script>
<style>
@import "~vue-sliding-pagination/dist/style/vue-sliding-pagination.css";

.c-sliding-pagination {
  text-align: center;
  margin: 1.5em 0;
}
</style>
