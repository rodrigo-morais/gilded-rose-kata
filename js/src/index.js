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

const upgrade_quality_backstage = item => {
  let MULTIPLIER = item.multiplier
  if (item.sell_in < 6) {
    MULTIPLIER = item.multiplier * 3
  } else if (item.sell_in < 11) {
    MULTIPLIER = item.multiplier * 2
  }

  if (item.sell_in < MIN_SELL_IN_LIMIT) {
    return Object.assign({}, item, { quality: 0 })
  }
  else {
    if (item.quality + MULTIPLIER < MAX_QUALITY_LIMIT) {
      return Object.assign({}, item, { quality: item.quality + MULTIPLIER })
    }
    else {
      return Object.assign({}, item, { quality: MAX_QUALITY_LIMIT })
    }
  }
}

const upgrade_quality = item => {
  if (item.quality + item.multiplier < MAX_QUALITY_LIMIT) {
    switch (item.name) {
      case 'Backstage passes to a TAFKAL80ETC concert':
        return upgrade_quality_backstage(item)
      default:
        return Object.assign({}, item, { quality: item.quality + item.multiplier })
    }
  } else {
    return Object.assign({}, item, { quality: MAX_QUALITY_LIMIT })
  }
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
