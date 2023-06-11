// pages/werun/werun.js
// pages/userSetting/userSetting.js
const app = getApp()
var Server = require("../../server/Server.js")

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
    var toOpenid = options.toOpenid
    this.setData({
      toOpenid:toOpenid
    })

    this.getWeRunSteps();

  },

  getWeRunSteps: function(){
    wx.login({
      success: res=>{
        if(res.code){
          wx.getWeRunData({
            success: (result) => {
              var iv = result.iv;
              var encryptedData = result.encryptedData;

              Server.encryptWeRunData(encryptedData, iv, res.code, stepRes=>{
                console.log(stepRes)
                this.setData({
                  openid: stepRes.data.data.werunUser.openid,
                  todayStep: stepRes.data.data.step,
                  totalStep: stepRes.data.data.werunUser.steps
                })
              })

            },
          })
        }
      }
    });
  },

  doGiveFriendSubmit: function(e){
    var step = e.detail.value.giveStep
    var toOpenid = this.data.toOpenid //'1Xlkc5BFJq417066fASkFPigwRZ1';
    var openid = this.data.openid
    if(!step || step > this.data.totalStep || step <= 0){
      wx.showToast({
        title: '数据错误',
      })
      return
    }
    
    Server.giveSteps(toOpenid, openid, step, res=>{
      console.log(res)
      //赠送成功
      this.getWeRunSteps();
    })

  },

  //兑换积分
  doSubmit: function(e){
    var pointStep = e.detail.value.pointStep;
    if(!pointStep || pointStep > this.data.totalSteps || pointStep<=0){
      wx.showToast({
        title: '数据错误',
      })
      return;
    }
    //登录，并兑换
  Server.login(res=>{
      var resData = res.data.data;
      if(resData.openid){//登录成功，获取用户信息
        Server.changePoints(resData.openid,pointStep, res=>{
          //兑换成功
          this.getWeRunSteps();
        });
      }
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
    var openid = this.data.openid;
    return {
      title: '好兄弟，把你的步数赠送给我吧',
      imageUrl: '../../images/icon/sSport.png',
      path:'/pages/werun/werun?toOpenid='+openid
    }
  }
})