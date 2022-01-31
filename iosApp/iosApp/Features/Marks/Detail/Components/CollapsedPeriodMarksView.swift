//
//  CollapsedPeriodMarksView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 31.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CollapsedPeriodMarksView: View {
    var text: String
    var period: MarkUnitPeriods
    
    @State private var isOpen: Bool
    
    init(text: String, period: MarkUnitPeriods, initiallyOpen: Bool = false) {
        self.text = text
        self.period = period
        self.isOpen = initiallyOpen
    }
    
    func calcTotalExpected() -> String? {
        let all = period.all.reduce(0.0) { $0 + Double($1.value) }
        if all == 0 { return nil }
        let amount = period.all.count
        let total = Double(all) / Double(amount)
        return String(format: "%.2f", total)
    }
    
    func calcTotal() -> String? {
        return period.total != nil ? String(Int(truncating: period.total!)) : nil
    }
    
    var body: some View {
        let totalExpected = calcTotalExpected()
        let total = calcTotal()
        
        HeaderView(text: text, isOpen: $isOpen)
        CollapsedView(isOpen: $isOpen) {
            VStack(spacing: 12) {
                ForEach(period.all, id: \.self) { mark in
                    MarkRow(theme: mark.name, date: mark.date, mark: mark.value)
                }
                
                if totalExpected != nil {
                    PrimaryMarkRow(name: "Средний балл", value: totalExpected!, accent: true).padding(.top, 5)
                }
                
                if total != nil {
                    PrimaryMarkRow(name: "Итоговая оценка", value: total!, accent: true).padding(.top, 5)
                }
            }
        }
    }
}

private struct HeaderView: View {
    var text: String
    @Binding var isOpen: Bool
    
    var body: some View {
        Button(action: { withAnimation { isOpen.toggle() } }) {
            HStack {
                Text(text)
                    .font(.custom("AvenirNext-Regular", size: 18))
                    .foregroundColor(.textPrimary)
                
                Spacer()
                
                Image("chevron_icon")
                    .resizable()
                    .frame(width: 18, height: 18)
                    .rotationEffect(.degrees(isOpen ? 0 : -90))
                    .animation(.spring())
            }
            .padding(.horizontal, 20)
            .padding(.vertical, 15)
        }
    }
}

private struct CollapsedView<Content: View>: View {
    @Binding var isOpen: Bool
    @ViewBuilder var content: () -> Content
    
    @ViewBuilder
    var body: some View {
        Group {
            if isOpen {
                content()
            } else {
                EmptyView()
            }
        }.animation(.spring())
    }
}

private struct MarkRow: View {
    var theme: String
    var date: Kotlinx_datetimeLocalDateTime
    var mark: Int32
    
    var body: some View {
        HStack {
            Text(theme)
                .font(.custom("AvenirNext-Regular", size: 16))
                .foregroundColor(.secondary)
            
            Spacer()
            
            Text(FormatHelper.shared.formatMarkDate(date: date))
                .font(.custom("AvenirNext-Regular", size: 13))
                .foregroundColor(.lightPrimary)
                .padding(.trailing, 15)
            
            Text(String(mark))
                .font(.custom("AvenirNext-Regular", size: 16))
                .foregroundColor(.contrast)
        }
        .padding(.horizontal, 20)
        .frame(maxWidth: .infinity)
    }
}

struct CollapsedPeriodMarksView_Previews: PreviewProvider {
    static var previews: some View {
        CollapsedPeriodMarksView(
            text: "1 четверть",
            period: MarkUnitPeriods(
                all: [],
                total: KotlinDouble(double: 1)
            )
        )
    }
}
