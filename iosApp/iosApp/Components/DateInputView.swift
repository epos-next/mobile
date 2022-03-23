//
//  DateInputView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 23.03.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DateInputView: View {
    @Binding var date: Date
    var placeholder: String = ""
    
    @State private var showDatePicker = false

    
    var body: some View {
            DatePicker(placeholder, selection: $date, displayedComponents: .date)
            .foregroundColor(Color.secondary)
            .padding(.horizontal, 20)
    }
}

struct DateInputView_Previews: PreviewProvider {
    static var previews: some View {
        DateInputView(date: .constant(Date.init()))
    }
}
