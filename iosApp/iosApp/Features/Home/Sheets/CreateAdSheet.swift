//
//  CreateAdSheet.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 23.03.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct CreateAdSheet: View {
    @State private var content: String = ""
    @State private var date = Date()
    @State private var loading = false
    
    @EnvironmentObject var marksObservable: MarksObservable
    @EnvironmentObject var adObservable: AdvertisementObservable
    @EnvironmentObject var bottomSheetObservable: BottomSheetObservable
    
    private func handleSubmit() {
        if loading { return }
        
        if content.isEmpty || date < Date() { return }
        
        loading = true
        adObservable.reducer.createAd(
            content: content,
            date: dateToKtx(date)
        )
        
        bottomSheetObservable.advertisement = .hidden
    }
    
    var body: some View {
        VStack(spacing: 20) {
            SheetTitleView("Новое объявление")
            FilledInputView(text: $content, placeholder: "Объявление")
            DateInputView(date: $date, placeholder: "Дата")
            MainButton("Создать", action: { handleSubmit() }, isDisabled: false, isLoading: loading)
        }.frame(
            minHeight: 0,
            maxHeight: .infinity,
            alignment: .topLeading
        )
        .padding(.horizontal, 20)
        .padding(.top, 30)
    }
}

struct CreateAdSheet_Previews: PreviewProvider {
    static var previews: some View {
        CreateAdSheet()
    }
}
