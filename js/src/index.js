const { MIN_QUALITY_LIMIT, MAX_QUALIT_LIMIT, MIN_SELL_IN_LIMIT } = require('./constants');

const create_item = (
  name, sell_in, quality, degrade_quality = '-', degrade = true
) => ({
  name,
  sell_in,
  quality,
  degrade_quality,
  degrade,
  multiplier: 1
}) 

const update_quality = function (items = []) {
  return items.map(item => {
    if (item.name != 'Aged Brie' && item.name != 'Backstage passes to a TAFKAL80ETC concert') {
      if (item.quality > MIN_QUALITY_LIMIT) {
        if (item.name != 'Sulfuras, Hand of Ragnaros') {
          item.quality = item.quality - 1
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
    if (item.name != 'Sulfuras, Hand of Ragnaros') {
      item.sell_in = item.sell_in - 1;
    }
    if (item.sell_in < 0) {
      if (item.name != 'Aged Brie') {
        if (item.name != 'Backstage passes to a TAFKAL80ETC concert') {
          if (item.quality > MIN_QUALITY_LIMIT) {
            if (item.name != 'Sulfuras, Hand of Ragnaros') {
              item.quality = item.quality - 1
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
