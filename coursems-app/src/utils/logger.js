const ERROR = 1
const WARN = 2
const INFO = 3
const DEBUG = 4
const TYPE = INFO

const logger = {
  error (page, msg) {
    if (TYPE >= ERROR) {
      console.log(this.getdate() + ' ERROR --- [ ' + page + ' ]:')
      console.log(msg)
    }
  },
  warn (page, msg) {
    if (TYPE >= WARN) {
      console.log(this.getdate() + ' WARN --- [ ' + page + ' ]:')
      console.log(msg)
    }
  },
  info (page, msg) {
    if (TYPE >= INFO) {
      console.log(this.getdate() + ' INFO --- [ ' + page + ' ]:')
      console.log(msg)
    }
  },
  debug (page, msg) {
    if (TYPE >= DEBUG) {
      console.log(this.getdate() + ' DEBUG --- [ ' + page + ' ]:')
      console.log(msg)
    }
  },
  getdate () {
    var date = new Date()
    return date.getFullYear() + '-' +
      (date.getMonth() + 1) + '-' +
      date.getDate() + ' ' +
      date.getHours() + ':' +
      date.getMinutes() + ':' +
      date.getSeconds()
  }
}

export default logger
