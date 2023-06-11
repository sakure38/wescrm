
const app = getApp();

const OPENID = '__openid__';
const PARENT_OPENID = "__parent__openid__";
const SESSION_KEY = "__session__key__";

function getOpenid() {
  return wx.getStorageSync(OPENID);
}

function setOpenid(openid){
  wx.setStorageSync(OPENID, openid)
}

function getSessionKey() {
  return wx.getStorageSync(SESSION_KEY);
}

function setSessionKey(sessionKey){
  wx.setStorageSync(SESSION_KEY, sessionKey)
}

function getParentOpenid() {
  return wx.getStorageSync(PARENT_OPENID);
}

function setParentOpenid(parentOpenid){
  wx.setStorageSync(PARENT_OPENID, parentOpenid)
}

module.exports = {
  getOpenid:getOpenid,
  setOpenid:setOpenid,
  getParentOpenid:getParentOpenid,
  setParentOpenid: setParentOpenid,
  setSessionKey: setSessionKey,
  getSessionKey: getSessionKey
}
