//
//  PrimaryMarkRow.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 31.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct PrimaryMarkRow: View {
    var name: String
    var value: String
    var accent: Bool = true
    var valueActive: Bool = true
    
    var body: some View {
        let nameColor = accent ? Color.textPrimary : Color.secondary
        let valueColor = valueActive ? Color.contrast : Color.lightContrast
        
        HStack {
            Text(name)
                .font(.custom("AvenirNext-Regular", size: 16))
                .foregroundColor(nameColor)
                .lineLimit(1)
            
            Spacer()
            
            Text(value)
                .font(.custom("AvenirNext-Regular", size: 16))
                .foregroundColor(valueColor)
        }.padding(.horizontal, 20)
    }
}

struct PrimaryMarkRow_Previews: PreviewProvider {
    static var previews: some View {
        Group {
            PrimaryMarkRow(name: "Средняя оценка за год", value: "4.23", accent: true, valueActive: true)
            PrimaryMarkRow(name: "Средняя оценка за год", value: "4.23", accent: false, valueActive: true)
            PrimaryMarkRow(name: "Средняя оценка за год", value: "4.23", accent: false, valueActive: false)
        }.padding(10)
    }
}
