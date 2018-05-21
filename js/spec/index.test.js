const { MIN_QUALITY_LIMIT, MAX_QUALITY_LIMIT, MIN_SELL_IN_LIMIT } = require('../src/constants')
const { create_item, update_quality } = require('../src/index')

describe('Gilded Rose', () => {
  describe('#update_quality', () => {
    describe('standard items', () => {
      const items = new Array(1,2).map(number => create_item(
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
        const items = new Array(1,2).map(number => create_item(
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
        const items = new Array(1,2).map(number => create_item(
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
        const items = new Array(1,2).map(number => create_item(
          `Item ${number}`,
          MIN_SELL_IN_LIMIT,
          25
        ), [])

        it('returns all items with less one sell_in and less two of quality points', () => {
          update_quality(items).forEach(item => {
            expect(item.quality).toBe(23)
            expect(item.sell_in).toBe(MIN_SELL_IN_LIMIT - 1)
          })
        })

        describe('when multiplier is 2', () => {
          const items = [
            create_item('Conjured', MIN_SELL_IN_LIMIT, 25)
          ]

          it('returns item with less four of quality points', () => {
            update_quality(items).forEach(item => {
              expect(item.quality).toBe(21)
              expect(item.sell_in).toBe(MIN_SELL_IN_LIMIT - 1)
            })
          })
        })
      })

      describe('when multiplier is 2', () => {
        const items = [
          create_item('Conjured', 5, 25, { multiplier: 2 })
        ]

        it('returns all items with less two of quality points', () => {
          update_quality(items).forEach(item => {
            expect(item.quality).toBe(23)
            expect(item.sell_in).toBe(4)
          })
        })
      })
    })

    describe('no degraded to "Sulfuras" item', () => {
      const items = [
        create_item('Sulfuras', 10, 25)
      ]

      it('returns the same items', () => {
        update_quality(items).forEach(item => {
          expect(item.quality).toBe(25)
          expect(item.sell_in).toBe(10)
        })
      })
    })

    describe('increase quality', () => {
      describe('degrade quality is positive', () => {
        describe('quality is less than maximun', () => {
          const items = [
            create_item('Aged Brie', 10, 25)
          ]

          it('returns quality bigger than before', () => {
            update_quality(items).forEach(item => {
              expect(item.quality).toBe(27)
              expect(item.sell_in).toBe(9)
            })
          })
        })

        describe('quality is the maximun', () => {
          const items = [
            create_item('Aged Brie', 10, MAX_QUALITY_LIMIT, { degrade_quality: '+' })
          ]

          it('returns same quality', () => {
            update_quality(items).forEach(item => {
              expect(item.quality).toBe(MAX_QUALITY_LIMIT)
              expect(item.sell_in).toBe(9)
            })
          })
        })

        describe('item is "Backstage passes"', () => {
          describe('sell in is less than 11 days', () => {
            describe('quality is less than maximun', () => {
              const items = [
                create_item('Backstage passes', 10, 25, { degrade_quality: '+' }),
              ]

              it('returns quality with more 2 points', () => {
                update_quality(items).forEach(item => {
                  expect(item.quality).toBe(27)
                  expect(item.sell_in).toBe(9)
                })
              })
            })

            describe('quality is the maximun', () => {
              const items = [
                create_item('Backstage passes', 10, MAX_QUALITY_LIMIT, { degrade_quality: '+' }),
              ]

              it('returns same quality', () => {
                update_quality(items).forEach(item => {
                  expect(item.quality).toBe(MAX_QUALITY_LIMIT)
                  expect(item.sell_in).toBe(9)
                })
              })
            })

            describe('quality is one point less the maximun', () => {
              const items = [
                create_item('Backstage passes', 10, MAX_QUALITY_LIMIT -1, { degrade_quality: '+' }),
              ]

              it('returns same quality', () => {
                update_quality(items).forEach(item => {
                  expect(item.quality).toBe(MAX_QUALITY_LIMIT)
                  expect(item.sell_in).toBe(9)
                })
              })
            })
          })

          describe('sell in is less than 6 days', () => {
            describe('quality is less than maximun', () => {
              const items = [
                create_item('Backstage passes', 5, 25, { degrade_quality: '+' })
              ]

              it('returns quality with more 3 points', () => {
                update_quality(items).forEach(item => {
                  expect(item.quality).toBe(28)
                  expect(item.sell_in).toBe(4)
                })
              })
            })

            describe('quality is the maximun', () => {
              const items = [
                create_item('Backstage passes', 5, MAX_QUALITY_LIMIT)
              ]

              it('returns same quality', () => {
                update_quality(items).forEach(item => {
                  expect(item.quality).toBe(MAX_QUALITY_LIMIT)
                  expect(item.sell_in).toBe(4)
                })
              })
            })

            describe('quality is one point less the maximun', () => {
              const items = [
                create_item('Backstage passes', 5, MAX_QUALITY_LIMIT -1),
              ]

              it('returns same quality', () => {
                update_quality(items).forEach(item => {
                  expect(item.quality).toBe(MAX_QUALITY_LIMIT)
                  expect(item.sell_in).toBe(4)
                })
              })
            })

            describe('quality is one point less the maximun', () => {
              const items = [
                create_item('Backstage passes', 5, MAX_QUALITY_LIMIT - 2),
              ]

              it('returns same quality', () => {
                update_quality(items).forEach(item => {
                  expect(item.quality).toBe(MAX_QUALITY_LIMIT)
                  expect(item.sell_in).toBe(4)
                })
              })
            })

            describe('quality drops to 0 after concert', () => {
              const items = [
                create_item('Backstage passes', 0, 25)
              ]

              it('returns 0 quality', () => {
                update_quality(items).forEach(item => {
                  expect(item.quality).toBe(0)
                })
              })
            })
          })
        })
      })
    })
  })
})
