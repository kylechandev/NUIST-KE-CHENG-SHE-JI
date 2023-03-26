import HTMLAudioElement from './HTMLAudioElement'

const HAVE_NOTHING = 0
const HAVE_METADATA = 1
const HAVE_CURRENT_DATA = 2
const HAVE_FUTURE_DATA = 3
const HAVE_ENOUGH_DATA = 4

const _innerAudioContext = new WeakMap()
const _src = new WeakMap()
const _loop = new WeakMap()
const _autoplay = new WeakMap()
export default class Audio extends HTMLAudioElement {
    HAVE_NOTHING = HAVE_NOTHING
    HAVE_METADATA = HAVE_METADATA
    HAVE_CURRENT_DATA = HAVE_CURRENT_DATA
    HAVE_FUTURE_DATA = HAVE_FUTURE_DATA
    HAVE_ENOUGH_DATA = HAVE_ENOUGH_DATA
    readyState = HAVE_NOTHING

    constructor(url) {
        super()

        _src.set(this, '')

        const innerAudioContext = wx.createInnerAudioContext()

        _innerAudioContext.set(this, innerAudioContext)

        innerAudioContext.onCanplay(() => {
            this.dispatchEvent({ type: 'load' })
            this.dispatchEvent({ type: 'loadend' })
            this.dispatchEvent({ type: 'canplay' })
            this.dispatchEvent({ type: 'canplaythrough' })
            this.dispatchEvent({ type: 'loadedmetadata' })
            this.readyState = HAVE_CURRENT_DATA
        })
        innerAudioContext.onPlay(() => {
            this.dispatchEvent({ type: 'play' })
        })
        innerAudioContext.onPause(() => {
            this.dispatchEvent({ type: 'pause' })
        })
        innerAudioContext.onEnded(() => {
            this.dispatchEvent({ type: 'ended' })
            this.readyState = HAVE_ENOUGH_DATA
        })
        innerAudioContext.onError(() => {
            this.dispatchEvent({ type: 'error' })
        })

        if (url) {
            _innerAudioContext.get(this).src = url
        }

        this._volume = innerAudioContext.volume
        this._muted = false;
    }

    load() {
        console.warn('HTMLAudioElement.load() is not implemented.')
    }

    play() {
        _innerAudioContext.get(this).play()
    }

    pause() {
        _innerAudioContext.get(this).pause()
    }

    destroy() {
        _innerAudioContext.get(this).destroy()
    }

    canPlayType(mediaType = '') {
        if (typeof mediaType !== 'string') {
            return ''
        }

        if (mediaType.indexOf('audio/mpeg') > -1 || mediaType.indexOf('audio/mp4')) {
            return 'probably'
        }
        return ''
    }

    get currentTime() {
        return _innerAudioContext.get(this).currentTime
    }

    set currentTime(value) {
        _innerAudioContext.get(this).seek(value)
    }

    get duration() {
        return _innerAudioContext.get(this).duration;
    }

    get src() {
        return _src.get(this)
    }

    set src(value) {
        _src.set(this, value)
        _innerAudioContext.get(this).src = value
    }

    get loop() {
        return _innerAudioContext.get(this).loop
    }

    set loop(value) {
        _innerAudioContext.get(this).loop = value
    }

    get autoplay() {
        return _innerAudioContext.get(this).autoplay
    }

    set autoplay(value) {
        _innerAudioContext.get(this).autoplay = value
    }

    get paused() {
        return _innerAudioContext.get(this).paused
    }

    get volume() {
        return this._volume;
    }

    set volume(value) {
        this._volume = value;
        if (!this._muted) {
            _innerAudioContext.get(this).volume = value;
        }
    }

    get muted() {
        return this._muted;
    }

    set muted(value) {
        this._muted = value;
        if (value) {
            _innerAudioContext.get(this).volume = 0;
        } else {
            _innerAudioContext.get(this).volume = this._volume;
        }
    }

    cloneNode() {
        const newAudio = new Audio()
        newAudio.loop = _innerAudioContext.get(this).loop
        newAudio.autoplay = _innerAudioContext.get(this).autoplay
        newAudio.src = this.src
        return newAudio
    }
}
