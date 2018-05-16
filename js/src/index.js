const { MIN_QUALITY_LIMIT, MAX_QUALITY_LIMIT, MIN_SELL_IN_LIMIT } = require('./constants');

const create_item = (
  name,
  sell_in,
  quality,
  {
    degrade_quality = '-',
    degrade = true,
    multiplier = 1
} = {}) => ({
  name,
  sell_in,
  quality,
  degrade_quality,
  degrade,
  multiplier
}) 

const degrade_quality = item => {
  const MULTIPLIER = item.sell_in > MIN_SELL_IN_LIMIT ? item.multiplier : item.multiplier * 2
  return Object.assign({}, item, { quality: item.quality - MULTIPLIER < 0 ? 0 : item.quality - MULTIPLIER })
}

const upgrade_quality = item => {
  if (item.quality < MAX_QUALITY_LIMIT) {
    item.quality = item.quality + 1

    if (item.name == 'Backstage passes to a TAFKAL80ETC concert') {
      if (item.sell_in < 11) {
        if (item.quality < MAX_QUALITY_LIMIT) {
          item.quality = item.quality + 1
        }
      }
      if (item.sell_in < 6) {
        if (item.quality < MAX_QUALITY_LIMIT) {
          item.quality = item.quality + 1
        }
      }
      if (item.sell_in < MIN_SELL_IN_LIMIT) {
        item.quality = item.quality - item.quality
      }
    }
    if (item.name === 'Aged Brie' &&
      item.quality < MAX_QUALITY_LIMIT) {
      item.quality = item.quality + 1
    }
  }

  return item
}

const degrade_sell_in = item => Object.assign({}, item, { sell_in: item.sell_in - 1 })

const update_item = item => {
  switch (item.degrade_quality) {
    case '+':
      return upgrade_quality(degrade_sell_in(item))
    default:
      return degrade_quality(degrade_sell_in(item))
  }
}

const update_quality = (items = []) =>
  items.map(item => item.degrade ? update_item(item) : item, [])

module.exports = { create_item, update_quality };
