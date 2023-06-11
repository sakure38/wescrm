// pages/category/category.js
const app = getApp()
var Server = require("../../server/Server.js")

Page({

  /**
   * 页面的初始数据
   */
  data: {
    contextUrl: app.CONTEXT_URL,
    products: [],
    pageNum:1,
    hasMore:true,

    curCategory:0,
    categoryList:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    Server.queryCategory(res=>{
      console.log(res)
      this.setData({
        categoryList: res.data.data
      })
    })

    this.queryProducts();
  },

  queryProducts: function(){
    if(!this.data.hasMore){return}
    var pageNum = this.data.pageNum;
    Server.queryProducts(pageNum, this.data.curCategory, res =>{
      console.log(res)
      var products = res.data.data.items;
      var hasMore = false;
      if(products.length > 0){
        hasMore = true;
      }
      this.setData({
        hasMore:hasMore,
        pageNum: this.data.pageNum + 1,
        products:this.data.products.concat(products)
      })
    })
  },

  changeCategory: function  (e) {
    this.setData({
      curCategory: e.target.id,
      products: [],
      pageNum:1,
      hasMore:true,
    })
    this.queryProducts();
  },

  fecthMore: function(){
    this.queryProducts()
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})