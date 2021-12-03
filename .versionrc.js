'use strict'

module.exports = {
  bumpFiles: [
    {
      filename: 'VERSION.txt',
      updater: require('./scripts/release/bumpVersion')
    },
    {
      filename: 'README.md',
      updater: require('./scripts/release/changeReadmeVersion')
    }
  ]
}
