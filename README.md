# TerminalEmulator

TerminalEmulator是一款终端模拟器，主要是模拟Linux的部分语句。主要包括cd、cp、rm、wc、wx、mkdir、rmkdir、ls、pwd、echo、date、history等语句。同时为了提高程序的运行效率，本实例中添加了多线程操作。

# 入门

TerminalEmulator包括date、operate、shell、time、main。

date主要是对输入的指令进行切分，获取指令的类型和参数。

time主要是对时间类进行定义，方便指令执行的使用。

operate主要是各条指令的实现。

shell主要是终端指令的控制和终端界面的输出。

main主要是对整个程序的管理。

# 文件结构

bin是配置文件和一些需要的包。

src是所有的源代码。

# 支持平台

TerminalEmulator基于JavaSE1.8。如果想要运行TerminalEmulator推荐下载Java编译器，并且需要pio、mysql-connector等包的支持。同时，需要注意处理文件和网页的格式。

# 疑问

如果您发现了诸如崩溃、意外行为或类似的问题，请访问[issue tracker](https://github.com/ranmengyuan/TerminalEmulator/issues)方便交流。

谢谢！
mengyuan
