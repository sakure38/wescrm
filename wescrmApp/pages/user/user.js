// pages/user/user.js
const app = getApp()
var Server = require("../../server/Server.js")
var Cache = require("../../server/Cache.js")

Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //先登录，再调用业务
    Server.login(r=>{
      Server.getUserInfo(res=>{
          console.log(res)
          this.setData({
            userInfo: res.data.data
          })
      })
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  //跳转到我的订单页面
  toOrders: function(e) {
    var status = e.currentTarget.dataset.id
    wx.navigateTo({
      url: '/pages/orders/orders?status='+status
    })
  },

  //进入个人设置页面
  tosetting: function(e) {
    wx.navigateTo({
      url: '/pages/userSetting/userSetting',
    })
  },

  //提现页面
  tocash: function(e){
    wx.navigateTo({
      url: '/pages/cash/cash',
    })
  },

  //进入微信运动页面
  towerun: function(e) {
      wx.navigateTo({
        url: '/pages/werun/werun',
      })
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