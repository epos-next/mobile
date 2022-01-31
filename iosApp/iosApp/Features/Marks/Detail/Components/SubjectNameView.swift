//
//  SubjectName.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 31.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SubjectNameView: View {
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    
    var text: String
    
    var body: some View {
        HStack {
            Button(action: { presentationMode.wrappedValue.dismiss() }) {
                Image("back_icon")
                    .resizable()
                    .frame(width: 26, height: 26)
            }
            
            Text(FormatHelper.shared.formatSubjectName(name: text))
                .font(.custom("AvenirNext-Medium", size: 24))
                .foregroundColor(.textPrimary)
                .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
        }.padding(.horizontal, 20)
    }
}

struct SubjectName_Previews: PreviewProvider {
    static var previews: some View {
        SubjectNameView(text: "Физика")
    }
}
