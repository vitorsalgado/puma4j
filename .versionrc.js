'use strict'

module.exports = {
  bumpFiles: [
    {
      filename: 'VERSION.txt',
      updater: require('./scripts/bumpVersion')
    }
  ]
}
