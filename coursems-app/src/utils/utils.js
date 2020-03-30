export function randomString () {
  var expect = 6 // 期望的字符串长度
  var str = Math.random().toString(36).substring(2)
  while (str.length < expect) {
    str = Math.random().toString(36).substring(2)
  }
  return str.substring(0, expect)
}
