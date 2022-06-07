//
//  DetailScreen.swift
//  kviewmodel-swiftui
//
//  Created by Алексей Гладков on 07.06.2022.
//

import Foundation
import SwiftUI

struct DetailScreen: View {
    @Environment(\.dismiss) var dismiss
    
    var body: some View {
        VStack {
            Text("Hello, Detail Page")
                .padding(EdgeInsets(top: 0, leading: 0, bottom: 16, trailing: 0))
            Button("Dismiss View") {
                dismiss()
            }
        }
    }
}
