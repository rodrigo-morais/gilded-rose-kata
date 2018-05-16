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

const update_quality = function (items = []) {
  return items.map(item => {
    if (item.degrade_quality === '-') {
      if (item.quality > MIN_QUALITY_LIMIT) {
        if (item.degrade) {
          item.quality = item.quality - item.multiplier
        }
      }
    } else {
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
        }
      }
    }
    if (item.degrade) {
      item.sell_in = item.sell_in - 1;
    }
    if (item.sell_in < MIN_SELL_IN_LIMIT) {
      if (item.name != 'Aged Brie') {
        if (item.name != 'Backstage passes to a TAFKAL80ETC concert') {
          if (item.quality > MIN_QUALITY_LIMIT) {
            if (item.degrade) {
              item.quality = item.quality - item.multiplier
            }
          }
        } else {
          item.quality = item.quality - item.quality
        }
      } else {
        if (item.quality < MAX_QUALITY_LIMIT) {
          item.quality = item.quality + 1
        }
      }
    }
    return item;
  }, []);
}

module.exports = { create_item, update_quality };
