const { MIN_QUALITY_LIMIT, MAX_QUALIT_LIMIT, MIN_SELL_IN_LIMIT } = require('../src/constants')
const { create_item, update_quality } = require('../src/index')

describe('Gilded Rose', () => {
  describe('#create_item', () => {
    const NAME = 'Item 1'
    const SELL_IN = 10
    const QUALITY = 25
    const DEGRADE_QUALITY = '+'
    const DEGRADE = false

    it('returns the item with default values when optional values are not informed', () => {
      const item = create_item(NAME, SELL_IN, QUALITY)
      expect(item.name).toBe(NAME)
      expect(item.sell_in).toBe(SELL_IN)
      expect(item.quality).toBe(QUALITY)
      expect(item.degrade_quality).toBe('-')
      expect(item.degrade).toBe(true)
    })

    it('returns the item with informed values when optional values are informed', () => {
      const item = create_item(NAME, SELL_IN, QUALITY, DEGRADE_QUALITY, DEGRADE)
      expect(item.name).toBe(NAME)
      expect(item.sell_in).toBe(SELL_IN)
      expect(item.quality).toBe(QUALITY)
      expect(item.degrade_quality).toBe(DEGRADE_QUALITY)
      expect(item.degrade).toBe(DEGRADE)
    })
  })
})
