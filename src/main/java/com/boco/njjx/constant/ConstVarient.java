package com.boco.njjx.constant;

/**
 * 采集驱动对外接口使用到的常量
 * @author 孙冠义
 *
 */
public interface ConstVarient {
	/**
	 * 接口函数调用成功返回值
	 */
	public final static int Drv_R_OK = 0; 
	/**
	 * 接口函数调用失败返回值的基准
	 */
    public final static int Drv_E_Base = 1000; 
    /**
     * 驱动已经被初始化错误
     */
    public final static int Drv_E_DriverAlreadyInit = Drv_E_Base + 1; 
    /**
     * 驱动没有初始化
     */
    public final static int Drv_E_DriverNotInit = Drv_E_Base + 2; 
    /**
     * 驱动识别串无效错误
     */
    public final static int Drv_E_DriverGUIDMissed = Drv_E_Base + 3; 
    /**
     * 无效的驱动识别号错误
     */
    public final static int Drv_E_InvalidDriverId = Drv_E_Base + 4; 
    /**
     * 无效的参数类型错误
     */
    public final static int Drv_E_InvalidParameter = Drv_E_Base + 5; 
    /**
     * 驱动已经运行错误
     */
    public final static int Drv_E_DriverAlreadyRunning = Drv_E_Base + 6; 
    /**
     * 驱动已经停止错误
     */
    public final static int Drv_E_DriverAlreadyStoped = Drv_E_Base + 7; 
    /**
     * 驱动程序执行调用发生错误
     */
    public final static int Drv_E_DriverImplement = Drv_E_Base + 8; 
    /**
     * 设备变量已经存在
     */
    public final static int Drv_E_DeviceVariantExists = Drv_E_Base + 9; 
    /**
     * 设备变量类型无效
     */
    public final static int Drv_E_InValidDeviceVariantType = Drv_E_Base + 10; 
    /**
     * 没有数据可读
     */
    public final static int Drv_E_DriverNoDataToRead = Drv_E_Base + 11; 
    /**
     * 下发数据失败
     */
    public final static int Drv_E_WriteDataFailed = Drv_E_Base + 12; 
    /**
     * 下发的设备变量索引无效
     */
    public final static int Drv_E_WriteDeviceIndexInValid = Drv_E_Base + 13; 
    /**
     * 注册通讯端口失败
     */
    public final static int Drv_E_RegisterCommPortFailed = Drv_E_Base + 14; 
    /**
     * 注册设备变量失败
     */
    public final static int Drv_E_RegisterDeviceVarFailed = Drv_E_Base + 15; 
    /**
     * 获取驱动信息失败
     */
    public final static int Drv_E_GetDriverTypeInfoFailed = Drv_E_Base + 16; 
    /**
     * 获取驱动变量信息失败
     */
    public final static int Drv_E_GetDriverVariantTypeInfoFailed = Drv_E_Base + 17; 
    /**
     * 获取驱动变量信息失败
     */
    public final static int Drv_E_SetDriverObserverFailed = Drv_E_Base + 18;
    /**
     * 驱动运行状态--基础
     */
    public final static int DRIVER_STATUS_BASE = 0x00000F00;
    /**
     * 驱动运行状态--未知
     */
    public final static int DRIVER_STATUS_UnKnown = DRIVER_STATUS_BASE + 1;
    /**
     * 驱动运行状态--装载失败
     */
    public final static int DRIVER_STATUS_LoadError = DRIVER_STATUS_BASE + 2;
    /**
     * 驱动运行状态--已加载
     */
    public final static int DRIVER_STATUS_Loaded = DRIVER_STATUS_BASE + 3;
    /**
     * 驱动运行状态--已停止
     */
    public final static int DRIVER_STATUS_Stoped = DRIVER_STATUS_BASE + 4;
    /**
     * 驱动运行状态--正在运行
     */
    public final static int DRIVER_STATUS_Running = DRIVER_STATUS_BASE + 5;

    /**
     * 通信端口状态
     */
    public final static int COMM_STATUS_BASE = 0x0000FF00;
    /**
     * 通信端口状态－－未知
     */
    public final static int COMM_STATUS_UnKnown = COMM_STATUS_BASE + 1;
    /**
     * 通信端口状态－－连接正常
     */
    public final static int COMM_STATUS_Connected = COMM_STATUS_BASE + 2;
    /**
     * 通信端口状态－－连接中断
     */
    public final static int COMM_STATUS_DisConnect = COMM_STATUS_BASE + 3;

    /**
     * 设备变量状态
     */
    public final static int ITEM_STATUS_BASE = 0x000FFF00;
    /**
     * 设备变量状态--未知
     */
    public final static int ITEM_STATUS_UnInit = ITEM_STATUS_BASE + 1;
    /**
     * 设备变量状态--正常
     */
    public final static int ITEM_STATUS_Normal = ITEM_STATUS_BASE + 2;
    /**
     * 设备变量状态--正在发送
     */
    public final static int ITEM_STATUS_Sendding = ITEM_STATUS_BASE + 3;
    /**
     * 设备变量状态--发送成功
     */
    public final static int ITEM_STATUS_SendSuccess = ITEM_STATUS_BASE + 4;
    /**
     * 设备变量状态--发送失败
     */
    public final static int ITEM_STATUS_SendFailed = ITEM_STATUS_BASE + 5;
}
