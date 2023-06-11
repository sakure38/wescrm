// pages/userSetting/userSetting.js
const app = getApp()
var Server = require("../../server/Server.js")
var Cache = require("../../server/Cache.js")

Page({

  /**
   * 页面的初始数据
   */
  data: {

    cards:[
      '身份证',
      '军官证'
    ],
    card:'请选择',
    banks:[
      '中国银行',
      '工商银行',
      '招商银行',
      '农业银行'
    ],
    bank:'请选择'

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    Server.login(r=>{
      Server.getUserInfo(res=>{
        var resData = res.data.data
        this.setData({
          userInfo: resData,
          card: resData.card,
          bank: resData.bank
        })
      })
    })

  },

  //实现保存
  doSubmit: function(e){
    //e.detai.value
    var user = e.detail.value;
    if(this.data.card != '请选择'){
      user.card = this.data.card;
    }
    if(this.data.bank != '请选择'){
      user.bank = this.data.bank;
    }
    Server.updateUserInfo(user, res=>{
      console.log(res)
      wx.showModal({
        title: '提示',
        content:'保存成功',
        showCancel:false
      })
    });

  },

  bindCardTypeChange: function(e) {
    var card = this.data.cards[e.detail.value]
    this.setData({
      card:card
    })
  },

  bindBankChange: function(e){
    var bank = this.data.banks[e.detail.value]
    this.setData({
      bank:bank
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