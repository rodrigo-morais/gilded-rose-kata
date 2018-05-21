const { MIN_QUALITY_LIMIT, MAX_QUALITY_LIMIT, MIN_SELL_IN_LIMIT } = require('./constants');

const create_item = (
  name,
  sell_in,
  quality,
) => ({
  name,
  sell_in,
  quality
}) 

const qualify_item = item => Object.assign({}, item, {
  quality_variant: item.name === 'Aged Brie' || item.name === 'Conjured' ? 2 : 1,
  degrade_quality: item.name === 'Aged Brie' || item.name === 'Backstage passes' ? '+' : '-',
  degrade: item.name !== 'Sulfuras'
})

const degrade_quality = item => {
  const QUALITY_VARIANT = item.sell_in > MIN_SELL_IN_LIMIT ? item.quality_variant : item.quality_variant * 2
  return Object.assign({}, item, { quality: item.quality - QUALITY_VARIANT < 0 ? 0 : item.quality - QUALITY_VARIANT })
}

const upgrade_quality_backstage = item => {
  let QUALITY_VARIANT = item.quality_variant
  if (item.sell_in < 6) {
    QUALITY_VARIANT = item.quality_variant * 3
  } else if (item.sell_in < 11) {
    QUALITY_VARIANT = item.quality_variant * 2
  }

  if (item.sell_in < MIN_SELL_IN_LIMIT) {
    return Object.assign({}, item, { quality: 0 })
  }
  else {
    if (item.quality + QUALITY_VARIANT < MAX_QUALITY_LIMIT) {
      return Object.assign({}, item, { quality: item.quality + QUALITY_VARIANT })
    }
    else {
      return Object.assign({}, item, { quality: MAX_QUALITY_LIMIT })
    }
  }
}

const upgrade_quality = item => {
  if (item.quality + item.quality_variant < MAX_QUALITY_LIMIT) {
    switch (item.name) {
      case 'Backstage passes':
        return upgrade_quality_backstage(item)
      default:
        return Object.assign({}, item, { quality: item.quality + item.quality_variant })
    }
  } else {
    return Object.assign({}, item, { quality: MAX_QUALITY_LIMIT })
  }
}

const degrade_sell_in = item => Object.assign({}, item, { sell_in: item.sell_in - 1 })

const update_item = item =>
  item.degrade_quality === '+' ?
    upgrade_quality(degrade_sell_in(item)) : 
    degrade_quality(degrade_sell_in(item))

const update_quality = (items = []) =>
  items.map(item => {
    const newItem = qualify_item(item)

    return newItem.degrade ? update_item(newItem) : newItem
  }, [])

module.exports = { create_item, update_quality };
