//
//  TextInout.swift
//  iosApp
//
//  Created by Ярослав Зотов on 19.12.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

private struct CustomTextFieldStyle: TextFieldStyle {
    func _body(configuration: TextField<_Label>) -> some View {
        configuration
            .frame(height: 30)
            .padding(.horizontal, 15)
            .padding(.vertical, 13)
            .background(
                RoundedRectangle(cornerRadius: 10, style: .continuous)
                    .stroke(Color.lightPrimary, lineWidth: 1.25)
            )
    }
}

struct SecureInput: View {
    var placeholder: String
    @Binding var text: String
    
    @State private var isSecured: Bool = true
    
    var body: some View {
        ZStack(alignment: .trailing) {
            if isSecured {
                SecureField(placeholder, text: $text)
                    .textFieldStyle(CustomTextFieldStyle())
                    .font(.custom("AvenirNext-Medium", size: 18))
            } else {
                TextField(placeholder, text: $text)
                    .textFieldStyle(CustomTextFieldStyle())
                    .font(.custom("AvenirNext-Medium", size: 18))
            }
            
            Button(action: { isSecured.toggle() }) {
                Image(systemName: self.isSecured ? "eye.slash" : "eye")
                    .accentColor(.lightPrimary)
                    .padding(.trailing, 15)
            }
        }
    }
}

struct TextInput: View {
    var placeholder: String
    @Binding var text: String
    
    @State private var editing: Bool = false
    
    var body: some View {
        TextField(placeholder, text: $text, onEditingChanged: {edit in self.editing = edit})
            .textFieldStyle(CustomTextFieldStyle())
            .font(.custom("AvenirNext-Medium", size: 18))
    }
}

struct TextInput_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            TextInput(placeholder: "Text", text: .constant(""))
            SecureInput(placeholder: "Password", text: .constant(""))
        }
    }
}
