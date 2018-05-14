const { MIN_QUALITY_LIMIT, MAX_QUALITY_LIMIT, MIN_SELL_IN_LIMIT } = require('../src/constants')
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

  describe('#update_quality', () => {
    describe('standard items', () => {
      const items = new Array(1,10).map(number => create_item(
        `Item ${number}`,
        10,
        25
      ), [])

      it('returns all items with less one quality and sell_in points', () => {
        update_quality(items).forEach(item => {
          expect(item.quality).toBe(24)
          expect(item.sell_in).toBe(9)
        })
      })

      describe('when quality is maximum limit', () => {
        const items = new Array(1,10).map(number => create_item(
          `Item ${number}`,
          10,
          MAX_QUALITY_LIMIT
        ), [])

        it('returns all items with less one quality and sell_in points', () => {
          update_quality(items).forEach(item => {
            expect(item.quality).toBe(MAX_QUALITY_LIMIT - 1)
            expect(item.sell_in).toBe(9)
          })
        })
      })

      describe('when quality is minimum limit', () => {
        const items = new Array(1,10).map(number => create_item(
          `Item ${number}`,
          10,
          MIN_QUALITY_LIMIT
        ), [])

        it('returns all items with same quality and less one sell_in points', () => {
          update_quality(items).forEach(item => {
            expect(item.quality).toBe(MIN_QUALITY_LIMIT)
            expect(item.sell_in).toBe(9)
          })
        })
      })

      describe('when sell in is minimum limit', () => {
        const items = new Array(1,10).map(number => create_item(
          `Item ${number}`,
          MIN_SELL_IN_LIMIT,
          25
        ), [])

        it('returns all items with less one sell_in, less two of quality points and multiplier double', () => {
          update_quality(items).forEach(item => {
            expect(item.quality).toBe(23)
            expect(item.sell_in).toBe(MIN_SELL_IN_LIMIT - 1)
            expect(item.multiplier).toBe(2)
          })
        })
      })
    })
  })
})
