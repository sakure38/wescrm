// pages/product/product.js
// 获取应用实例
const app = getApp()
var Server = require("../../server/Server.js")
var Cache = require("../../server/Cache.js")

Page({

  /**
   * 页面的初始数据
   */
  data: {
    contextUrl: app.CONTEXT_URL,
    count: 1

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var pid = options.id;

    if(pid){
      Server.getProduct(pid, res=>{
          this.setData({
            product: res.data.data
          })
      })
    }

    var parentOpenid = options.parentOpenid;
    if(parentOpenid){
      Cache.setParentOpenid(parentOpenid)
    }

  },

  changeCount: function(e) {
    this.setData({
      count: e.target.id
    })
  },

  quickBuy: function(e){
    var pid = this.data.product.id
    var count = this.data.count
    wx.navigateTo({
      url: '/pages/payment/payment?pid=' + pid +'&count='+count
    })

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
  onShareAppMessage() {
    var product = this.data.product
    var path =  '/pages/product/product?id='+product.id
    if(Cache.getOpenid()){
      path = path + '&parentOpenid='+ Cache.getOpenid()
    }
    return {
      title: product.name,
      imageUrl: this.data.contextUrl + '/file/attachment/'+product.picture+'/s',
      path:path
    }
  }


})