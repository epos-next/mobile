//
//  DetailMarksScreenView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 31.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DetailMarksScreenView: View {
    var lesson: String
    var unit: MarkUnit
    
    func calcTotalExpected() -> String? {
        let all = unit.periods.reduce(0.0) { $0 + Double(truncating: $1.total ?? 0) }
        let amount = unit.periods.filter({ $0.total != nil }).count
        let n = !unit.periods.isEmpty ? round(all / Double(amount)) : nil
        return n != nil ? String(format: "%.2f", n!) : nil
    }
    
    func calcTotal() -> Int? {
        return unit.total != nil ? Int(round(Double(truncating: unit.total!))) : nil
    }
    
    @ViewBuilder
    var body: some View {
        let totalExpected = calcTotalExpected()
        let total = calcTotal()
        
        ScrollView {
            VStack(spacing: 0) {
                SubjectNameView(text: lesson).padding(.bottom, 15)
                
                if (totalExpected != nil) {
                    PrimaryMarkRow(name: "Средняя оценка за год", value: totalExpected!).padding(.bottom, 10)
                }
                
                if (total != nil) {
                    PrimaryMarkRow(name: "Выставленная оценка за год", value: String(total!)).padding(.bottom, 20)
                }
                
                ForEach(unit.periods.indices, id: \.self) { i in
                    CollapsedPeriodMarksView(text: "\(i + 1) четверть", period: unit.periods[i])
                }
            }
        }
        .navigationBarHidden(true)
        .navigationBarBackButtonHidden(true)
    }
}
