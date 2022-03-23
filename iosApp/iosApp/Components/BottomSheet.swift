//
//  BottomSheet.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 23.03.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import BottomSheet

public extension View {
    
    func primaryBottomSheet<mContent: View, bottomSheetPositionEnum: RawRepresentable>(
        position: Binding<bottomSheetPositionEnum>,
        @ViewBuilder content: () -> mContent
    ) -> some View where bottomSheetPositionEnum.RawValue == CGFloat, bottomSheetPositionEnum: CaseIterable {
        self.bottomSheet(
            bottomSheetPosition: position,
            options: [
                .swipeToDismiss,
                .tapToDismiss,
                .background(AnyView(Color.white)),
                .backgroundBlur(effect: .dark),
                .cornerRadius(20),
                .noBottomPosition,
                .absolutePositionValue
            ],
            content: content
        )
    }
}
