'use strict'

const VersionRegex = /\d+(\.\d+)+/ig

module.exports.readVersion = function (contents) {
  return contents
}

module.exports.writeVersion = function (contents, version) {
  return contents.replace(VersionRegex, version)
}
