//
//  TotalNumber.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 14.04.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct TotalNumber: View {
    var number: Int
    var active: Bool = true
    
    @Environment(\.colorScheme) var colorScheme
    
    @ViewBuilder
    var body: some View {
        let color = active ? Color.contrast : Color.lightContrast
        
        Text(String(number))
            .font(.custom("AvenirNext-Medium", size: 18))
            .foregroundColor(color)
    }
}

struct TotalNumber_Previews: PreviewProvider {
    static var previews: some View {
        TotalNumber(number: 5)
    }
}
