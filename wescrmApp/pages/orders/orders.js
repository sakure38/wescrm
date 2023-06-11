
const app = getApp()
var Server = require("../../server/Server.js")

// pages/orders/orders.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    contextUrl: app.CONTEXT_URL,
    orders:[],
    status:0,
    pageNum:1,
    hasMore: true
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getOrders()
  },

  getOrders: function(e){
    if(!this.data.hasMore){return}
    Server.login(r=>{
      Server.getOrders(this.data.pageNum, this.data.status, res=>{
        console.log(res)
        var orders = res.data.data.items
        if(orders.length > 0){
          this.setData({hasMore:true})
        }else{
          this.setData({hasMore:false})
        }
        this.setData({
          orders: this.data.orders.concat(orders),
          pageNum: this.data.pageNum + 1
        })
      })
    })
  },

  changeTab: function(e) {
    this.setData({
      status : e.target.id,
      pageNum:1,
      hasMore:true,
      orders:[]
    })
    this.getOrders()
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
    this.getOrders()
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})