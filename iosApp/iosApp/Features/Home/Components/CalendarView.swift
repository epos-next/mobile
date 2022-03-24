//
//  CalendarView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 29.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CalendarView: View {
    @EnvironmentObject var homeViewModel: HomeViewModel
    var onDaySelected: (_ date: Kotlinx_datetimeLocalDate) -> Void
    
    private func onDatySelectedHandler(day: Kotlinx_datetimeLocalDate) {
        homeViewModel.calendarDate = day
        onDaySelected(day)
    }
    
    var body: some View {
        let dates = CalendarKt.daysInMonthArray(date: homeViewModel.calendarDate)
        
        VStack(spacing: 0) {
            CalendarHeader().padding(.bottom, 15)
            WeekdayRow()
            
            ZStack(alignment: .topLeading) {
                SelectedDateIndicator(index: Int(dates.selectedIndex))
                VStack(spacing: 0) {
                    ForEach(0..<dates.items.count / 7, id: \.self) { r in
                        HStack(spacing: 0) {
                            ForEach(0..<7, id: \.self) { cell in
                                Button(action: { onDatySelectedHandler(day: dates.items[(r * 7) + cell].date) }) {
                                    CalendarItem(item: dates.items[(r * 7) + cell])
                                }
                            }
                        }
                    }
                }
            }
            .padding(.horizontal, 10)
        }
    }
}

private struct SelectedDateIndicator: View {
    var index: Int
    
    private func getColWidth() -> CGFloat {
        return (UIScreen.main.bounds.size.width - 20) / 7
    }
    
    private func getOffsetX() -> CGFloat {
        var x: CGFloat = index % 7 == 0 ? CGFloat(6) : CGFloat(index % 7 - 1)
        x *= getColWidth()
        return x + (getColWidth() - 34) / 2
    }
    
    private func getOffsetY() -> CGFloat {
        return (ceil(CGFloat(index) / 7) - 1) * 41 + 3.5
    }
    
    var body: some View {
        Circle()
            .fill(LinearGradient(
                gradient: .init(colors: [Color(hex: 0xFF949EFD), Color(hex: 0xFF656DFD)]),
                startPoint: .init(x: 0, y: 0),
                endPoint: .init(x: 1, y: 1)
            ))
            .frame(width: 34, height: 34)
            .padding(.top, getOffsetY())
            .padding(.leading, getOffsetX())
            .animation(.spring())
    }
}

private struct CalendarItem: View {
    var item: Item
    
    private func getColor() -> Color {
        var textColor = Color.lightPrimary
        if item.active { textColor = Color.textPrimary }
        if item.isSelected { textColor = Color.white }
        return textColor;
    }
    
    
    var body: some View {
        Text(String(item.date.dayOfMonth))
            .font(.custom("AvenirNext-Regular", size: 15))
            .foregroundColor(getColor())
            .frame(minWidth: 0, maxWidth: .infinity,  minHeight: 41, alignment: .center)
        
    }
}

private struct CalendarHeader: View {
    @EnvironmentObject var homeViewModel: HomeViewModel
    
    private func onMonthGoForward() {
        let date = homeViewModel.calendarDate
        
        if date.month.ordinal == 11 {
            homeViewModel.calendarDate = Kotlinx_datetimeLocalDate(
                year: date.year + 1,
                month: Kotlinx_datetimeMonth.january,
                dayOfMonth: date.dayOfMonth
            )
        } else {
            let month = Kotlinx_datetimeMonth.values().get(index: date.month.ordinal + 1)!
            homeViewModel.calendarDate = Kotlinx_datetimeLocalDate(
                year: date.year,
                month: month,
                dayOfMonth: month == Kotlinx_datetimeMonth.february && date.dayOfMonth > 28
                    ? 28
                    : date.dayOfMonth
            )
        }
    }
    
    private func onMonthGoBackward() {
        let date = homeViewModel.calendarDate
        
        if date.month.ordinal == 0 {
            homeViewModel.calendarDate = Kotlinx_datetimeLocalDate(
                year: date.year - 1,
                month: Kotlinx_datetimeMonth.december,
                dayOfMonth: date.dayOfMonth
            )
        } else {
            let month = Kotlinx_datetimeMonth.values().get(index: date.month.ordinal - 1)!
            homeViewModel.calendarDate = Kotlinx_datetimeLocalDate(
                year: date.year,
                month: month,
                dayOfMonth: month == Kotlinx_datetimeMonth.february && date.dayOfMonth > 28
                    ? 28
                    : date.dayOfMonth
            )
        }
    }
    
    var body: some View {
        HStack {
            let month = homeViewModel.calendarDate.month
            
            Button(action: { onMonthGoBackward() }) {
                Image("left_icon")
                    .resizable()
                    .frame(width: 24, height: 24)
                    .padding(.leading, 10)
            }
            
            Spacer()
            
            Text(FormatHelper.shared.month(month: month))
                .font(.custom("AvenirNext-regular", size: 16))
                .foregroundColor(Color.textPrimary)
            
            Spacer()
            
            Button(action: { onMonthGoForward() }) {
                Image("right_icon")
                    .resizable()
                    .frame(width: 24, height: 24)
                    .padding(.trailing, 10)
            }
        }
    }
}

private struct WeekdayRow: View {
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        HStack(spacing: 0) {
            ForEach(0..<ConstantsKt.weekDays.count) { i in
                let weekDay = ConstantsKt.weekDays[i] as! String
                
                Text(weekDay)
                    .font(.custom("AvenirNext-Regular", size: 15))
                    .frame(minWidth: 0, maxWidth: .infinity, alignment: .center)
                    .foregroundColor(colorScheme == .light ? .secondary : Color(hex: 0xFF999EA4))
            }
        }
    }
}

struct CalendarView_Previews: PreviewProvider {
    static var previews: some View {
        CalendarView(
            onDaySelected: { date in
                print(date)
            }
        )
            .environmentObject(HomeViewModel())
            .previewDevice(PreviewDevice(rawValue: "iPhone 12"))
    }
}
