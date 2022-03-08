package usedbooks.footer;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TeamInfoLabel {

	public static void set(JLabel cooperateLabel, Font font, Color color, JFrame jframe) {
		cooperateLabel = new JLabel("4조 일등을조 - 최준영, 이용기, 조정언", JLabel.CENTER);
		cooperateLabel.setBounds(110, 370, 400, 40);
		cooperateLabel.setOpaque(false); // 투명
		cooperateLabel.setFont(font);
		cooperateLabel.setForeground(color);
		jframe.add(cooperateLabel);

	}
}
