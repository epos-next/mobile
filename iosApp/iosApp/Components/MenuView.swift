//
//  MenuView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 23.03.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct MenuView: View {
    @Binding var text: String
    var options: [String]
    var placeholder: String = ""
    
    var body: some View {
        Menu {
            ForEach(options, id: \.self){ option in
                Button(option) {
                    text = option
                }
            }
        } label: {
            FilledInputView(text: $text, placeholder: placeholder)
        }
    }
}

struct MenuView_Previews: PreviewProvider {
    static var previews: some View {
        MenuView(text: .constant(""), options: ["Test 1", "Test 2"])
    }
}
