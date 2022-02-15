/**
 * @author Jiun Kim
 * created on 2022-02-14
 */
import {
  apiGetAllInvestPreset,
  apiPostNewInvest,
  apiPutInvestStatus,
  apiGetInvestStatusByChild,
  apiGetAllInvest,
  apiPostNewPrice,
  apiGetSharePrice,
  apiPostNews,
  apiGetAllNew,
} from '@/api/investAPI.js';

const investStore = {
  namespaced: true,
  state: {
    presetList: [], // 프리셋 리스트
    investList: [], // 투자 목록
    investment: null, // 투자 디테일
    priceList: [], //가격 목록
    newsList: [], // 뉴스 목록
    isWritedNews: false, // 오늘의 뉴스 등록 여부
  },
  getters: {
    getPresetList: (state) => state.presetList,
    getInvestList: (state) => state.investList,
    getPriceList: (state) => state.priceList,
    getNewsList: (state) => state.newsList,
    getIsWritedNews: (state) => state.isWritedNews,
  },
  mutations: {
    SET_PRESET_LIST(state, payload) {
      state.presetList = payload;
    },
    SET_INVEST_LIST(state, payload) {
      state.investList = payload;
    },
    SET_INVESTMENT(state, payload) {
      state.investment = payload;
    },
    SET_PRICE_LIST(state, payload) {
      state.priceList = payload;
    },
    SET_NEWS_LIST(state, payload) {
      state.newsList = payload;
    },
    SET_IS_WRITED_NEWS(state, payload) {
      state.isWritedNews = payload;
    },
  },
  actions: {
    /**
     * 투자 프리셋 목록 불러오기
     *
     * @param {*} param0
     */
    async vuexUpdatePreset({ commit }) {
      await apiGetAllInvestPreset((resp) => {
        commit('SET_PRESET_LIST', resp.data);
      });
    },
    /**
     * 새로운 투자 생성
     *
     * @param {*} param0
     * @param {*} invest 새 투자
     */
    async vuexAddNewInvest(invest) {
      await apiPostNewInvest(invest, (resp) => {
        console.log(resp, '투자 등록 완료');
        // 투자 목록 다시 업데이트
        this.vuexUpdateInvestList(invest.parentSeq);
      });
    },
    /**
     * 투자 현황 업데이트
     *
     * @param {*} param0
     * @param {*} status 투자 현황
     */
    async vuexUpdateInvestStatus(status, investSeq, childSeq) {
      await apiPutInvestStatus(status, (resp) => {
        console.log(resp, '현황 업데이트 완료');
        // 현황 다시 불러오기
        this.vuexGetInvestStatus(investSeq, childSeq);
      });
    },
    /**
     * 자녀별 투자 종목별 현황 조회
     *
     * @param {*} param0
     * @param {*} investSeq 투자 종목 키
     * @param {*} childSeq 자녀 키
     */
    async vuexGetInvestStatus({ commit }, investSeq, childSeq) {
      await apiGetInvestStatusByChild(investSeq, childSeq, (resp) => {
        commit('SET_INVESTMENT', resp.data);
      });
    },
    /**
     * 투자 목록 불러오기
     *
     * @param {*} param0
     * @param {*} parentSeq 부모 키
     */
    async vuexUpdateInvestList({ commit }, parentSeq) {
      await apiGetAllInvest(parentSeq, (resp) => {
        commit('SET_INVEST_LIST', resp.data);
      });
    },
    /**
     * 새로운 투자 가격 정보 추가
     *
     * @param {*} priceInfo 가격 정보
     */
    async vuexAddPrice(priceInfo) {
      await apiPostNewPrice(priceInfo, (resp) => {
        console.log(resp, '새로운 가격 정보 추가 완료');
        this.vuexGetPriceList(priceInfo.investSeq);
      });
    },
    /**
     * 투자 종목별 시세 목록 조회
     * @param {*} param0
     * @param {*} investSeq 투자 종목 키
     */
    async vuexGetPriceList({ commit }, investSeq) {
      await apiGetSharePrice(investSeq, (resp) => {
        commit('SET_PRICE_LIST', resp.data);
      });
    },
    /**
     * 뉴스 목록 불러오기
     *
     * @param {*} param0
     * @param {*} parentSeq 부모 키
     */
    async vuexUpdateNewsList({ commit }, parentSeq) {
      await apiGetAllNew(parentSeq, (resp) => {
        commit('SET_NEWS_LIST', resp.data);
      });
    },
    /**
     * 새로운 뉴스 등록
     *
     * @param {*} news 새 뉴스
     */
    async vuexAddNews({ commit }, news) {
      console.log(news);
      await apiPostNews(news, (resp) => {
        commit('SET_IS_WRITED_NEWS', true);
        console.log(resp, '뉴스 등록 완료');
        // 뉴스 다시 업데이트
        // this.vuexUpdateNewsList({ commit }, news.parentSeq);
      });
    },
  },
};

export default investStore;