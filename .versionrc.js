'use strict'

module.exports = {
  bumpFiles: [
    {
      filename: 'VERSION.txt',
      updater: require('./scripts/release/bumpVersion')
    },
    {
      filenam: 'README.md',
      updater: require('./scripts/release/changeReadmeVersion')
    }
  ]
}
