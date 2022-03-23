//
//  BottomSheetsState.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 23.03.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI

class BottomSheetObservable: ObservableObject {
    @Published var controlWork: ControlWorkBottomSheetPosition = .hidden
    @Published var advertisement: AdBottomSheetPosition = .hidden
}

public enum AdBottomSheetPosition: CGFloat, CaseIterable {
    case top = 800, middle = 280, hidden = 0
}

public enum ControlWorkBottomSheetPosition: CGFloat, CaseIterable {
    case top = 800, middle = 345, hidden = 0
}
