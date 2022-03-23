//
//  CreateTestReminderSheet.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 23.03.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import BottomSheet

struct CreateTestReminderSheet: View {
    @State private var name: String = ""
    @State private var subject: String = ""
    @State private var date = Date()
    @State private var loading = false
    
    @EnvironmentObject var marksObservable: MarksObservable
    @EnvironmentObject var controlWorkObservable: ControlWorkObservable
    @EnvironmentObject var bottomSheetObservable: BottomSheetObservable
    
    private func handleSubmit() {
        if loading { return }
        
        if name.isEmpty || subject.isEmpty || date < Date() { return }
        
        loading = true
        controlWorkObservable.reducer.createSchoolTest(
            name: name,
            subject: subject,
            date: dateToKtx(date)
        )
        
        bottomSheetObservable.controlWork = .hidden
    }
    
    var body: some View {
        VStack(spacing: 20) {
            SheetTitleView("Новая контрольная работа")
            FilledInputView(text: $name, placeholder: "Название")
            MenuView(text: $subject, options: marksObservable.reducer.getSubjectNames(), placeholder: "Предмет")
            DateInputView(date: $date, placeholder: "Дата")
            MainButton("Создать", action: { handleSubmit() })
        }.frame(
            minHeight: 0,
            maxHeight: .infinity,
            alignment: .topLeading
        )
        .padding(.horizontal, 20)
        .padding(.top, 30)
    }
}

struct CreateTestReminderSheet_Previews: PreviewProvider {
    static var previews: some View {
        CreateTestReminderSheet()
    }
}
