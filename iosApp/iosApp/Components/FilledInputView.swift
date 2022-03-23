//
//  FilledInputView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 23.03.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct FilledInputView: View {
    @Binding var text: String
    var placeholder: String = ""
    var suffixIcon: String? = nil
    
    var body: some View {
        HStack {
            TextField(placeholder, text: $text)
                .textFieldStyle(CustomTextFieldStyle())
            
            if suffixIcon != nil {
                Image(suffixIcon!)
                    .resizable()
                    .frame(width: 21, height: 21)
            }
           
        }
        .padding(.vertical, 13)
        .padding(.horizontal, 17)
        .background(
            RoundedRectangle(cornerRadius: 10, style: .continuous)
                .fill(Color.disabled)
        )
        .padding(.horizontal, 20)
    }
}

private struct CustomTextFieldStyle: TextFieldStyle {
    func _body(configuration: TextField<_Label>) -> some View {
        configuration
            .frame(minWidth: 0, maxWidth: .infinity)
            .font(.custom("AvenirNext-Regular", size: 16))
            .multilineTextAlignment(.leading)
            .foregroundColor(.textPrimary)
    }
}

struct FilledInputView_Previews: PreviewProvider {
    static var previews: some View {
        FilledInputView(text: .constant("Физика"))
    }
}
