// pages/payment/payment.js
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
    address:'请选择',
    date:'请选择'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var pid = options.pid
    var count = options.count

    if(pid){
      Server.getProduct(pid, res=>{
        var product = res.data.data;
        var total = count * product.price;
        this.setData({
          product: product,
          count:count,
          total:total
        })
      })
    }

  },

  //获取地址
  bindAddressChange: function(e){
    wx.chooseAddress({
      success: res=> {
        this.setData({
          address: res
        })
      },
    })
  },

  //获取日期
  bindDateChange: function(e){
    this.setData({
      date: e.detail.value
    })
  },

  //获取订单备注中的数据
  bindRemarkInput: function(e){
    this.setData({
      remark: e.detail.value
    })
  },

   //获取订单备注中的数据
   bindInvoiceInput: function(e){
    this.setData({
      invoice: e.detail.value
    })
  },

  //发起支付，创建订单
  wechatPay: function(e) {
    Server.login(res=>{
      // console.log(res)
      //完成订单的创建
      var pid = this.data.product.pid;
      var count = this.data.count;
      var address = this.data.address;
      var shippingTime = this.data.date;
      var invoice = this.data.invoice;
      var remark = this.data.remark;
      
      if(address && address.length < 6){
        wx.showToast({
          title: '地址不正确',
        })
        return;
      }
      address = [address.countryName,address.cityName,address.detailInfo,address.userName,address.telNumber].join(',');
      if(shippingTime == '请选择'){
        shippingTime = null
      }

      //创建订单
      Server.createOrder(pid, count, address, shippingTime,  invoice, remark, res=>{
        wx.showModal({
          title: '提示',
          content:'购买成功',
          success: res=>{
            wx.switchTab({
              url: '/pages/user/user',
            })
          }
        })
      });

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
  onShareAppMessage: function () {

  }
})