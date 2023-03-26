import { noop } from './util/index.js'

// TODO 需要 wx.getSystemInfo 获取更详细信息
const systemInfo = wx.getSystemInfoSync()

const system = systemInfo.system;
const platform = systemInfo.platform;
const language = systemInfo.language;

const android = system.toLowerCase().indexOf('android') !== -1;

const uaDesc = android ? 'Android; CPU Android 6.0' : 'iPhone; CPU iPhone OS 10_3_1 like Mac OS X';
const ua = `Mozilla/5.0 (${uaDesc}) AppleWebKit/603.1.30 (KHTML, like Gecko) Mobile/14E8301 MicroMessenger/6.6.0 MiniGame NetType/WIFI Language/${language}`;

const navigator = {
  platform,
  language: language,
  appVersion: `5.0 (${uaDesc}) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1`,
  userAgent: ua,
  onLine: true, // TODO 用 wx.getNetworkStateChange 和 wx.onNetworkStateChange 来返回真实的状态

  // TODO 用 wx.getLocation 来封装 geolocation
  geolocation: {
    getCurrentPosition: noop,
    watchPosition: noop,
    clearWatch: noop
  }
}

if (wx.onNetworkStatusChange) {
    wx.onNetworkStatusChange(function(event){
        navigator.onLine = event.isConnected;
    });
}

export default navigator
